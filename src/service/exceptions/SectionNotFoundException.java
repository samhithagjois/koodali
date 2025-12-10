package service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Section not found")  // 404
public class SectionNotFoundException extends Exception{

    public SectionNotFoundException(){
        super("""
                The section does not exist.
                Please check that your input format follows the follwoing RegEx:
                [BDEILMNPX][ELNRUX]_[BIAN][UE_D][LG123V].
                The first two letters are the country/city code, the next three letters are the batch.Please talk to Samhitha if you mess up.""");
    }
}
