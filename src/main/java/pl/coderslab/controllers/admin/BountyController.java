package pl.coderslab.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.dto.BountyDetails;
import pl.coderslab.services.BountyService;

import java.util.List;


@Controller
@RequestMapping("/admin/bounties")
public class BountyController {


    private final BountyService bountyService;


    @Autowired
    public BountyController(BountyService bountyService) {
        this.bountyService = bountyService;
    }


    @GetMapping("/")
        public String institutions(Model model){

        return "admin/bounties";
    }

    @GetMapping("/{id}")
        public String details(Model model, @PathVariable Long id){

        model.addAttribute("bountyDetail",bountyService.findBountyDetail(id));
        return "admin/bounties";
    }


    @ModelAttribute("bounties")
    public List<BountyDetails> listOfBounties(Model model){
        return bountyService.returnListOfBounties(); }




}
