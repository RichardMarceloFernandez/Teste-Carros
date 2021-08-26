import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarrosController {
    
    @Autowired
    jogoRepository jogoRepository;
    @Autowired
    devsResopitory devsResopitory;
    
   
    @GetMapping(value="/")
    public ModelAndView getInicial() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
    
    @GetMapping(value="/Carros")
    public ModelAndView getListaTodosCarros() {
        List<Jogo> lista01 = jogoRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("carros");
        modelAndView.addObject("lista01", lista01);
        return modelAndView;
    }

    @GetMapping(value="/cadastrocarros")
    public ModelAndView getCadastroCarros() {
        Carro carro = new Carro();
        ModelAndView modelAndView = new ModelAndView("cadastroCarro");
        modelAndView.addObject("carro", carro);
        return modelAndView;
    }
    
    @GetMapping(value="/detalhescarros/{id}")
    public ModelAndView getDetalhesCarros(@PathVariable Long id) {
        Carro carro = jogoRepository.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("detalhescarros");
        modelAndView.addObject("carro", carro);
        return modelAndView;
    }

    @GetMapping(value="/deletarCarro/{id}")
    public String getDeletarCarros(@PathVariable Long id) {
        jogoRepository.deleteById(id);
        return "redirect:/carros";
    }

    @GetMapping(value="/editarCarro/{id}")
    public ModelAndView getEditarCarros(@PathVariable Long id) {
        Carro carro = jogoRepository.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("cadastroCarros");
        modelAndView.addObject("carro", carro);
        return modelAndView;
   }

    @PostMapping(value="/adicionarCarro")
    public String postAdicionarCarros(Carro carro) {
        jogoRepository.save(carro);
        return "redirect:/carros";
    }

}
