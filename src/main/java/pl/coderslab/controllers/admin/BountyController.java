package pl.coderslab.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.services.BountyService;


@Controller
@RequestMapping("/admin/bounties")
public class BountyController {


    private final BountyService bountyService;


    @Autowired
    public BountyController(BountyService bountyService) {
        this.bountyService = bountyService;
    }


    @GetMapping("/")
        public String bounties(Model model){

        model.addAttribute("bounties",bountyService.returnListOfBounties());
        return "admin/bounty/bounties";
    }

    @GetMapping("/archived/")
    public String archived(Model model){

        model.addAttribute("archivedBounties",bountyService.returnListOfArchivedBounties());
        return "admin/bounty/archiveBounties";
    }

    @GetMapping("/{id}")
        public String details(Model model, @PathVariable Long id){

        model.addAttribute("bountyDetail",bountyService.findBountyDetail(id));
        return "admin/bounty/singleBounty";
    }


    @GetMapping("/receive/{id}")
        public String receive(@PathVariable Long id,Model model){

        bountyService.changeAttributeReceived(id);
        model.addAttribute("bountyDetail",bountyService.findBountyDetail(id));
        return "admin/bounty/singleBounty";
    }

    @GetMapping("/archive/{id}")
        public String archive(Model model, @PathVariable Long id){

        if(bountyService.isEnableToArchive(id)){
            bountyService.changeAttributeArchived(id);
        }else{
            return "redirect:/admin/bounties/"+id;
        }
        return "redirect:/admin/bounties/";
    }

}
