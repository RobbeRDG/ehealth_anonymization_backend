package be.kul.useraccess.controller.REST;

import be.kul.useraccess.Entity.ScriptAnonymizationResult;
import be.kul.useraccess.Service.UserAccessService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private UserAccessService userAccessService;

    @PostMapping("/scripts/upload")
    public ResponseEntity<Long> onScriptUpload(
            @RequestParam("file") MultipartFile scriptFile
            ) throws JsonProcessingException {
        //handle the script summary and return its id
        long scriptId = userAccessService.handleScriptUpload(scriptFile).getScriptId();

        return new ResponseEntity<>(scriptId, HttpStatus.OK);
    }

    @GetMapping("/test/result/{id}")
    public ResponseEntity<ScriptAnonymizationResult> onResultRequest(
            @PathVariable(value="id") String scriptIdString
    ){
        long scriptId = Long.parseLong(scriptIdString);
        ScriptAnonymizationResult anonymizationResult = userAccessService.handleScriptAnonymizationResultRequest(scriptId);

        return new ResponseEntity<>(anonymizationResult,HttpStatus.OK);
    }

}
