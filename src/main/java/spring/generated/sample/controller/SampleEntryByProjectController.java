package spring.generated.sample.controller;

import java.lang.String;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.generated.forms.SampleEntryByProjectForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.dictionary.ObservationHistoryList;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.organization.util.OrganizationTypeList;
import us.mn.state.health.lims.organization.valueholder.Organization;


@Controller
public class SampleEntryByProjectController extends BaseController {
  @RequestMapping(
      value = "/SampleEntryByProject",
      method = RequestMethod.GET
  )
  public ModelAndView showSampleEntryByProject(HttpServletRequest request,
      @ModelAttribute("form") SampleEntryByProjectForm form) {
    String forward = FWD_SUCCESS;
    if (form == null) {
    	form = new SampleEntryByProjectForm();
    }
    form.setFormAction("");
    
    Date today = Calendar.getInstance().getTime();
    String dateAsText = DateUtil.formatDateAsText(today);
    form.setReceivedDateForDisplay(dateAsText);
    form.setInterviewDate(dateAsText);

    Map<String, List<Dictionary>> formListsMapOfLists = new HashMap<String, List<Dictionary>>();
    Dictionary dictionary = null;
    List<Dictionary> listOfDictionary = new ArrayList<Dictionary>();
    List<IdValuePair> genders;
    genders = DisplayListService.getList(ListType.GENDERS);
    
    for (IdValuePair i : genders)  {
    	dictionary = new Dictionary();
    	dictionary.setId(i.getId());
    	dictionary.setDictEntry(i.getValue());
    	listOfDictionary.add( dictionary);
    }
    
    formListsMapOfLists.put("GENDERS", listOfDictionary);
    form.setFormLists(formListsMapOfLists);
    
    //Get EID Lists
    Map<String, List<Dictionary>> observationHistoryMapOfLists = new HashMap<String, List<Dictionary>>();
    observationHistoryMapOfLists.put("EID_WHICH_PCR", ObservationHistoryList.EID_WHICH_PCR.getList());
    observationHistoryMapOfLists.put("EID_SECOND_PCR_REASON", ObservationHistoryList.EID_SECOND_PCR_REASON.getList());
    observationHistoryMapOfLists.put("YES_NO", ObservationHistoryList.YES_NO.getList());
    observationHistoryMapOfLists.put("EID_TYPE_OF_CLINIC", ObservationHistoryList.EID_TYPE_OF_CLINIC.getList());
	form.setDictionaryLists(observationHistoryMapOfLists);
	
	//Get EID Sites
	Map<String, List<Organization>> organizationTypeMapOfLists = new HashMap<String, List<Organization>>();
	organizationTypeMapOfLists.put("EID_ORGS_BY_NAME", OrganizationTypeList.EID_ORGS_BY_NAME.getList());
	organizationTypeMapOfLists.put("EID_ORGS", OrganizationTypeList.EID_ORGS.getList());
	form.setOrganizationTypeLists(organizationTypeMapOfLists);
	    
	//form.set "ProjectData.EIDSites", OrganizationTypeList.EID_ORGS.getList());
	//PropertyUtils.setProperty(dynaForm, "ProjectData.EIDSitesByName", OrganizationTypeList.EID_ORGS_BY_NAME.getList());
	        
    BaseErrors errors = new BaseErrors();
    if (form.getErrors() != null) {
    	errors = (BaseErrors) form.getErrors();
    }
    ModelAndView mv = checkUserAndSetup(form, errors, request);

    if (errors.hasErrors()) {
    	return mv;
    }

    return findForward(forward, form);}
  
  @RequestMapping(
	      value = "/SampleEntryByProject",
	      method = RequestMethod.POST
	  )
	  public ModelAndView postSampleEntryByProject(HttpServletRequest request,
	      @ModelAttribute("form") SampleEntryByProjectForm form) {
	    
	    //String forward = "eid_entry";
	    String forward = FWD_SUCCESS;
	    
	    if (form == null) {
	    	form = new SampleEntryByProjectForm();
	    }
	    form.setFormName("sampleEntryByProjectForm");
	    form.setFormAction("");
	    

	    BaseErrors errors = new BaseErrors();
	    if (form.getErrors() != null) {
	    	errors = (BaseErrors) form.getErrors();
	    }
	    ModelAndView mv = checkUserAndSetup(form, errors, request);

	    if (errors.hasErrors()) {
	    	return mv;
	    }

	    return findForward(forward, form);}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("success".equals(forward)) {
      return new ModelAndView("sampleEntryByProjectDefinition", "form", form);
    } else if ("eid_entry".equals(forward)) {
      return new ModelAndView("sampleEntryEIDDefinition", "form", form);
    } else if ("vl_entry".equals(forward)) {
      return new ModelAndView("sampleEntryVLDefinition", "form", form);
    } else if ("fail".equals(forward)) {
      return new ModelAndView("homePageDefinition", "form", form);
    } else {
      return new ModelAndView("PageNotFound");
    }
  }

  protected String getPageTitleKey() {
    return null;
  }

  protected String getPageSubtitleKey() {
    return null;
  }
}
