package puzzle;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Controller
public class MyController {
    private static String UPLOAD_LOCATION="/opt/tomcat/webapps/puzzle/WEB-INF/pic/";
    private static String DEFAULT_PICTURE = "NOT.jpg";
    //private static String UPLOAD_LOCATION="src/main/webapp/WEB-INF/pic/";
    @Autowired
    private UserService userService;
    @Autowired
    private FormulaDiagnosisService formulaDiagnosisService;
    @Autowired
    private FormulaService formulaService;
    @Autowired
    private DiagnosisService diagnosisService;
    @Autowired
    private CompositionService compositionService;
    @Autowired
    private ComponentsService componentsService;
    @Autowired
    private RulesService rulesService;

    private List<FormulaDiagnosis> getFormulaDiagnoses(String search){
        List<FormulaDiagnosis> formulas = formulaDiagnosisService.getOnlyPriorFormulas(search);

        List<Long> idFormulas = new ArrayList<Long>();
        for (FormulaDiagnosis formula : formulas) {
            idFormulas.add(formula.getFormula().getId());
        }
        if (idFormulas.size() != 0) {
            List<Formula> notPriorFormulas = formulaDiagnosisService.getFormulasWithoutPrior(idFormulas, search);
            for (Formula formula : notPriorFormulas) {
                formulas.add(formulaDiagnosisService.getFormulaDiagnosis(formula.getId()));
            }
        } else {
            idFormulas = formulaDiagnosisService.getFormulaId(search);
            for(Long id: idFormulas){
                formulas.add(formulaDiagnosisService.getFormulaDiagnosis(id));
            }
        }
        return formulas;
    }

    private List<FormulaDiagnosis> getFormulaDiagnosesChecked(String search, Long[] idDiagnosis){
        List<Long> idFormulas = formulaDiagnosisService.getFormulas(idDiagnosis, search);
        List<FormulaDiagnosis> formulas = new ArrayList<FormulaDiagnosis>();

        List<Long> notIdFormulas = new ArrayList<Long>();
        if (idFormulas.size() > 0) {
            List<Formula> formulaList = formulaDiagnosisService.getOnlyPriorFormulasChecked(idFormulas);
            for (Formula formula : formulaList) {
                formulas.add(formulaDiagnosisService.getPriorDiagnosis(formula.getId()));
                notIdFormulas.add(formula.getId());
            }
        }

        if (notIdFormulas.size() != 0) {
            List<Formula> notPriorFormulas = formulaDiagnosisService.getFormulasWithoutPriorChecked(notIdFormulas, idFormulas);
            for (Formula formula : notPriorFormulas) {
                formulas.add(formulaDiagnosisService.getFormulaDiagnosis(formula.getId()));
            }
        } else {
            idFormulas = formulaDiagnosisService.getFormulas(idDiagnosis, search);
            for(Long id: idFormulas){
                formulas.add(formulaDiagnosisService.getFormulaDiagnosis(id));
            }
        }

        return formulas;
    }

    @RequestMapping("")
    public String index(Model model){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        CustomUser dbUser = userService.getUserByLogin(login);

        model.addAttribute("formula", getFormulaDiagnoses(""));
        model.addAttribute("search", "");
        model.addAttribute("diagnosis", diagnosisService.getDiagnosis());
        return "formula";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@RequestParam(required = false) String email, @RequestParam(required = false) String phone) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        CustomUser dbUser = userService.getUserByLogin(login);
        dbUser.setEmail(email);
        dbUser.setPhone(phone);

        userService.updateUser(dbUser);

        return "";
    }

    @RequestMapping("addFormula")
    public String addFormula(@RequestParam(value = "update", required = false) int isUpdate,
                             @RequestParam(value = "saveFile", required = false) int isSaveFile,
                             @Valid FileBucket fileBucket, BindingResult result,
                             @RequestParam(value = "formulaName", required = false) String formulasName, Model model)throws IOException {
        if(isSaveFile == 1){
            if (result.hasErrors()) {
                return "singleFileUploader";
            } else {
                MultipartFile multipartFile = fileBucket.getFile();
                //Save file
                FileCopyUtils.copy(fileBucket.getFile().getBytes(),  new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
                String fileName = multipartFile.getOriginalFilename();
                model.addAttribute("formula", formulaService.getFormulas());
                model.addAttribute("fileName", fileName);
                model.addAttribute("components", componentsService.getComponents());
                return "addFormula";
            }
        }
        else
        if (isUpdate == 0) {
            model.addAttribute("formula", formulaService.getFormulas());
            model.addAttribute("components", componentsService.getComponents());
            model.addAttribute("fileName", "-");
        }
        else {
            Formula formulaToUpdate = formulaService.getFormulaByName(formulasName);
            String fileName = formulaToUpdate.getPic();
            model.addAttribute("formula", formulaService.getFormulas());
            model.addAttribute("fileName", fileName);
            model.addAttribute("components", componentsService.getComponents());
            model.addAttribute("formulaToUpdate", formulaToUpdate);
        }
        return "addFormula";
    }


    @RequestMapping("addFormula/save")
    public String saveFormula(@RequestParam Map<String, String> newComponents, Model model){
        String fileName = DEFAULT_PICTURE;
        if (!newComponents.get("file").equals("-")){
            //fileName = IMG_FOLDER + newComponents.get("file");
            fileName = newComponents.get("file");
        }
        Formula newFormula = new Formula(newComponents.get("name"), fileName, newComponents.get("info"));
        String result = "";
        //if newFormula is not in list
        if (newComponents.size() !=0) {
            if (!newComponents.get("name").equals("")) {
                if (formulaService.getFormulaByName(newFormula.getName()) == null) {
                    formulaService.insert(newFormula);
                }
                else {
                    newFormula = formulaService.getFormulaByName(newFormula.getName());
                    formulaService.delete(newFormula);
                    newFormula = new Formula(newFormula.getName(), fileName, newComponents.get("info"));
                    formulaService.insert(newFormula);
                }

                    Long id = formulaService.getFormulaByName(newFormula.getName()).getId();
                    for (String comp : newComponents.keySet()) {
                        // add composition (formula-component-value) in DB
                        try {
                            String[] valUnit = newComponents.get(comp).split("-");
                            double value = Double.parseDouble(valUnit[0]);
                            String unit ="";
                            try {
                                unit = valUnit[1];
                            }catch (ArrayIndexOutOfBoundsException e){
                                unit ="";
                            }
                            Components component = componentsService.getComponent(Long.parseLong(comp));
                            Composition composition = compositionService.findComponent(newFormula.getId(), component.getId());
                            if (composition == null) {
                                composition = new Composition(formulaService.getFormulaByName(newFormula.getName()), component, value, unit);
                            }
                            else {
                                composition.setValue(value);
                                composition.setUnits(unit);
                            }
                            compositionService.insert(composition);
                        } catch (Exception e) {
                        }
                    }

                    //determining the type of formula
                   List<Diagnosis> diagnosises = new ArrayList<Diagnosis>();
                    Diagnosis diagnosis = null;
                    List<Integer> variants = rulesService.getVariants();
                    for (Integer ruleVar : variants) {
                        List<Rules> rules = rulesService.findByVar(ruleVar);
                        boolean isDiagnosis = true;
                        for (Rules rule : rules) {

                            Composition composition = compositionService.findComponent(formulaService.getFormulaByName(newFormula.getName()).getId(), rule.getComponents().getId());
                            try {
                                if (!rule.isRuleRight(composition.getValue())) {
                                    isDiagnosis = false;
                                    break;
                                }
                                diagnosis = rule.getDiagnosis();
                            } catch (Exception e) {
                                isDiagnosis = false;
                                break;
                            }
                        }

                        if (isDiagnosis) {
                            //add formula-diagnosis in DB
                                FormulaDiagnosis formulaDiagnosis = formulaDiagnosisService.getFormulaDiagnosis(newFormula.getId(), diagnosis.getId());
                            if(formulaDiagnosis == null){
                                formulaDiagnosis = new FormulaDiagnosis(formulaService.getFormulaByName(newFormula.getName()), diagnosis, 0);
                            }
                            formulaDiagnosisService.save(formulaDiagnosis);
                        }
                    }
                }
        }
        model.addAttribute("result", result);
        return "formula";
    }


    @RequestMapping(value = "info")
    public String info(@RequestParam(value = "id", required = false) Long formulaId, /*@PathVariable(value = "id") Long formulaId, */Model model) {
        model.addAttribute("composition", compositionService.getCompositionsForFormula(formulaId));
        model.addAttribute("diagnosis", formulaDiagnosisService.getDiagnosis(formulaId));
        model.addAttribute("priorDiagnosis", formulaDiagnosisService.getPriorDiagnosis(formulaId));
        model.addAttribute("formula", formulaService.getFormula(formulaId));
        return "info";
    }


    @RequestMapping(value = "delete{id}")
    public String deleteFormula(@PathVariable(value = "id") Long formulaId, Model model) {
        Formula toDelete = formulaService.getFormula(formulaId);
        formulaService.delete(toDelete);
        return "formula";
    }

    @RequestMapping(value = "deleteUser/{login}")
    public String deleteUser(@PathVariable(value = "login") String login, Model model) {
        CustomUser toDelete = userService.getUserByLogin(login);
        userService.delete(toDelete);

        model.addAttribute("formula", getFormulaDiagnoses(""));
        model.addAttribute("search", "");
        model.addAttribute("diagnosis", diagnosisService.getDiagnosis());
        return "formula";
    }

    @RequestMapping(value = "info/save")
    public String saveInfo(@RequestParam Map<String, String> paramList, Model model){
        try{
            FormulaDiagnosis formulaDiagnosis = formulaDiagnosisService.getPriorDiagnosis(Long.parseLong(paramList.get("idFormula")));
            formulaDiagnosis.setPrior(0);
            formulaDiagnosisService.save(formulaDiagnosis);
        } catch (Exception e){

        }

        FormulaDiagnosis formulaDiagnosis = formulaDiagnosisService.getFormulaDiagnosis(Long.parseLong(paramList.get("idFormula")), Long.parseLong(paramList.get("idDiagnosis")));
        formulaDiagnosis.setPrior(1);
        formulaDiagnosisService.save(formulaDiagnosis);
        return "info";
    }

    @RequestMapping(value = "formula")
    public String checkDiagnosis(@RequestParam Map<String, String> findFormula,
                                 @RequestParam(required = false) String search,
                                 @RequestParam(value = "checkDiagnosis[]", required = false) Long[] checkDiagnosis, Model model){

        if(search == null){
            search = "";
        }
             if (checkDiagnosis == null) {
                 model.addAttribute("formula", getFormulaDiagnoses(search));
             } else {
                    model.addAttribute("formula", getFormulaDiagnosesChecked(search, checkDiagnosis));
                 }

        model.addAttribute("diagnosis", diagnosisService.getDiagnosis());
        model.addAttribute("search", search);

        return "formula";
    }

    @RequestMapping("users")
    public String getUsers(Model model){
        List<CustomUser> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping("about")
    public String about(){
        return "about";
    }

    @RequestMapping("users/save")
    public String saveUsers(@RequestParam Map<String, String> newUser, Model model){
        String login = newUser.get("login");
        CustomUser user = null;
        try {
            user = userService.getUserByLogin(login);
        }
        catch (Exception e){ }
        if (user == null) {
            try {
                String password = sha1(newUser.get("password"));
                if (!login.equals("") && (!password.equals(""))) {
                    if (newUser.get("role").equalsIgnoreCase("admin")) {
                        user = new CustomUser(login, password, UserRole.ROLE_ADMIN);
                    } else {
                        user = new CustomUser(login, password, UserRole.ROLE_USER);
                    }
                    user.setEmail(newUser.get("email"));
                    user.setPhone(newUser.get("phone"));
                }

            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            }
        }
        else{
            try {
                user.setPassword(sha1(newUser.get("password")));
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {}

            user.setEmail(newUser.get("email"));
            user.setPhone(newUser.get("phone"));
            if (newUser.get("role").equalsIgnoreCase("admin")) {
                user.setRole(UserRole.ROLE_ADMIN);
            } else {
                user.setRole(UserRole.ROLE_USER);
            }
        }
        userService.addUser(user);
        return "users";
    }


    public static String sha1(String Param) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest SHA = MessageDigest.getInstance("SHA");
        SHA.reset();
        SHA.update(Param.getBytes("UTF-8"), 0, Param.length());
        byte[] sha1hash = SHA.digest();
        return  bytesToHexStr(sha1hash);
    }

    // Преобразование байтового массива в hex-строку
    public static String bytesToHexStr(byte[] raw) {
        char[] kDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        int length = raw.length;
        char[] hex = new char[length * 2];
        for (int i = 0; i < length; i++) {
            int value = (raw[i] + 256) % 256;
            int highIndex = value >> 4;
            int lowIndex = value & 0x0f;
            hex[i * 2 + 0] = kDigits[highIndex];
            hex[i * 2 + 1] = kDigits[lowIndex];
        }
        return new String(hex);
    }
}
