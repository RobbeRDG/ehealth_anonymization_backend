package be.kul.useraccess.controller.REST;

import be.kul.useraccess.Service.UserAccessService;
import be.kul.useraccess.Utils.Enums.SupportedScriptLanguage;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private UserAccessService userAccessService;

    @PostMapping("/scripts/upload")
    public ResponseEntity<String> uploadScript(
            @RequestParam("file") MultipartFile scriptFile
            ) throws JsonProcessingException {
        return userAccessService.uploadScript(scriptFile);
    }

}
