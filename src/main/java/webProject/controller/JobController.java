package webProject.controller;

import webProject.framework.api.APIResponse;
import webProject.framework.controller.BaseController;
import webProject.model.dto.JobDTO;
import webProject.model.entity.Category;
import webProject.model.entity.Job;
import webProject.service.CategoryService;
import webProject.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Controller
@RequestMapping("job")
public class JobController extends BaseController {
    private static Logger LOG = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private JobService jobService;

    @Autowired
    private CategoryService categoryService;

    /**
     * Metodo per la creazione del lavoro tramite json
     * POST formato che si aspetta - {"name":"job1", "metadataJsonString":"{}", "callbackUrl":"", "catId":1}
     *
     * @param jobDTO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST, headers = {JSON_API_CONTENT_HEADER})
    public @ResponseBody
    APIResponse submitJob(@RequestBody JobDTO jobDTO) throws Exception {
        Long catId = jobDTO.getCatId();

        if(catId==null) {
            throw new IllegalArgumentException("categoryId is required to prioritize");
        }

        Category category = categoryService.findById(catId);

        Job job = new Job();
        job.setName(jobDTO.getName());
        job.setMetadataJson(jobDTO.getMetadataJsonString());
        job.setCategory(category);
        job.setCallbackUrl(jobDTO.getCallbackURL());
        job.setSubmitTime(new Date(System.currentTimeMillis()));
        job.setStatus(Job.Status.NEW);
        job.setRetryCount(0);

        jobService.insert(job);
        return APIResponse.toOkResponse(job);
    }

    /**
     * metodo per catturare lo stato del lavoro tramite ID
     * * GET
     *
     * @param jobId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/status/{jobId}", method = RequestMethod.GET)
    public @ResponseBody
    APIResponse jobStatus(@PathVariable Long jobId) throws Exception {
        Job job = jobService.findById(jobId);
        return APIResponse.toOkResponse(job);
    }
}
