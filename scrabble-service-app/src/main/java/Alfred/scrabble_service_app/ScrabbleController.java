package Alfred.scrabble_service_app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScrabbleController {

    @RequestMapping("/")
    public String index() {
        return "Welcome to Alfred's Scrabble service!";
    }
	
    @RequestMapping("/rest/scrabble")
    public Scrabble scrabble(@RequestParam(value="list", defaultValue="") String list) {
        return new Scrabble(list);
    }
    
    @RequestMapping("/rest/scrabble/all")
    public ScrabbleAll scrabbleAll(@RequestParam(value="list", defaultValue="") String list) {
        return new ScrabbleAll(list);
    }
}
