package com.fh.controller.app.project;

import com.fh.controller.base.BaseController;
import com.fh.core.ErrorCode;
import com.fh.core.HttpJsonResult;
import com.fh.core.StringUtil;
import com.fh.entity.Page;
import com.fh.service.system.appuser.AppuserManager;
import com.fh.service.web.project.ProjectManager;
import com.fh.service.web.projectinnterfacerel.ProjectInnterfaceRelManager;
import com.fh.service.web.projectinnterfacerel.impl.ProjectInnterfaceRelService;
import com.fh.service.web.resource.ResourceManager;
import com.fh.util.PageData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/appProject")
public class AppProjectController extends BaseController {

    @Resource(name = "projectService")
    private ProjectManager projectService;

    @Resource(name = "appuserService")
    private AppuserManager appuserService;

    @Resource(name="projectinnterfacerelService")
    private ProjectInnterfaceRelService projectinnterfacerelService;

    @Resource(name="resourceService")
    private ResourceManager resourceService;

    /**
     * @throws null
     * @api {GET} /appProject/getInfoByMap根据地图编码查询汇总信息,人员,进度,安全,质量,环保(大平台首页汇总显示)
     * @apiName getInfoByMap
     * @apiGroup appProject
     *
     * @apiParam {String}
     * @apiParamExample {json} Request-Example
     * example
     *     {
     *     		"uid": "123456",                                            ----->uid
     *     		"mapBianma": "456414153456615"                              ----->对应(数据字典)编码
     *     }
     *
     * @apiSuccess {Boolean} success true or false，表示请求是否成功
     * @apiUse code
     * @apiSuccessExample {json} Success-Response:
     *     {
     *     "message": null,
     *     "total": 0,
     *     "data": [
     *           {
     *           ------------------------------项目列表------------------------------------------------
     *              "projectList": [
     *                    {
     *                       "LOCATION_BIANMA": "0030101",                       --->地图编码(数据字典)
     *                       "LONGITUDE": "111",                                 --->经度
     *                       "PROJECT_URL": "http://192.168.199.133:8080",       --->项目公网地址
     *                       "LATITUDE": "222",                                  --->纬度
     *                       "PROJECT_NAME": "测试项目1",                         -->项目部名称
     *                       "PROJECT_ID": "c2db0562ce5f4bd9a3794c7c56fc59c4"    --->(主键忽略)
     *                    },
     *                    {
     *                       "LOCATION_BIANMA": "0030101",
     *                       "LONGITUDE": "111",
     *                       "PROJECT_URL": "http://192.168.199.133:8080",
     *                       "LATITUDE": "111",
     *                       "PROJECT_NAME": "测试项目2",
     *                       "PROJECT_ID": "f5fb8606d45e43dfb141d9c89b2522b2"
     *                    }
     *              ],
     *              ----------------------------人员管理------------------------------------------------
     *              "depPersonCount": 6,                            ------> 项目部人员总数
     *              "projectCount": 2,                              ------> 项目总数
     *              "outCompanyCount":12,                           ------> (外包公司)施工班组总数
     *              "outStaffCount": 2,                             ------> (外包公司人员)施工班组人员总数
     *              ----------------------------进度管理------------------------------------------------
     *              "sumProgress':30,                               ------> 总进度
     *              "outputValue":500,                              ------> 总产值
     *              ----------------------------质量管理------------------------------------------------
     *              "qualityCount":5,                               ------> 质量监控点总数
     *              "qualityNotCount":1,                            ------> 未完成
     *              "qualityHasCount":3,                            ------> 正在完成
     *              "qualityDoCount":2,                             ------> 已完成
     *              ----------------------------安全管理------------------------------------------------
     *              "securityCount":10,                             ------> 安全监控点总数
     *              "securityNotCount":5,                           ------> 未完成
     *              "securityHasCount:5,                            ------> 正在完成
     *              "securityDoCount":0,                            ------> 已完成
     *              ----------------------------环保管理------------------------------------------------
     *              "environmentCount":10,                           ------> 环保监控点
     *              "destroyCount":5,                               ------> 已破坏监控点
     *              "recoverCount":5                                ------> 正在恢复监控点
     *           }
     *     ],
     *     "success": true,
     *     "code": "0000",
     *     "msg": null,
     *     "backUrl": null,
     *     "rows": [],
     *     "footer": []
     *     }
     */
    @RequestMapping(value = "/getInfoByMap", method = RequestMethod.GET)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public HttpJsonResult<List<PageData>> getInfoByMap(HttpServletRequest request) {
        HttpJsonResult<List<PageData>> jsonResult = new HttpJsonResult<List<PageData>>();
        try {
            PageData pd = this.getPageData(request);
            String uid = pd.getString("uid");
            PageData user = new PageData();
            user.put("USER_ID", uid);
            user = appuserService.findById(user);
            if (user == null) {
                jsonResult.setMessage("该用户不存在！");
                jsonResult.setCode(ErrorCode.USER_NOT_EXIST);
                return jsonResult;
            }
            String mapBianma = pd.getString("mapBianma");
            if (StringUtil.isEmpty(mapBianma, true)) {
                jsonResult.setMessage("您没传地图城市对应的编码");
                jsonResult.setCode(ErrorCode.PARAM_ERROR);
                return jsonResult;
            }

            List<PageData> result = new ArrayList<PageData>();//最后返回给总接口的数据
            List<PageData> proList = projectService.getProjectInfoByMapBianma(mapBianma);//根据地图编码查询项目信息
            PageData otherInfo = new PageData();//其他信息(人员，质量，安全，进度，环保)
            otherInfo.put("projectList", proList);//项目列表(根据地图城市显示的项目)
            PageData newPd = projectinnterfacerelService.getPersonManagerCount0(mapBianma);//获取汇总信息
            newPd.put("projectCount", proList.size()); //项目总数
            result.add(otherInfo);
            result.add(newPd);

            jsonResult.setData(result);
            jsonResult.setMessage("");
            jsonResult.setMsg("");
            jsonResult.setBackUrl("");
            return jsonResult;
        } catch (Exception e) {
            jsonResult.setSuccess(false);
            jsonResult.setMessage("查询异常：" + e);
            return jsonResult;
        }

    }



    /**
     * @throws null
     * @api {GET} /appProject/getProjectListByMap根据地图编码(点击城市对应的编码)查询项目部列表，获取经纬度地图显示
     * @apiName getProjectListByMap
     * @apiGroup appProject
     *
     * @apiParam {String}
     * @apiParamExample {json} Request-Example
     * example
     *     {
     *     		"uid": "123456",                                            ----->uid
     *     		"mapBianma": "456414153456615"                              ----->对应(数据字典)编码
     *     }
     *
     * @apiSuccess {Boolean} success true or false，表示请求是否成功
     * @apiUse code
     * @apiSuccessExample {json} Success-Response:
     *     {
     *     "message": null,
     *     "total": 0,
     *     "data": [
     *           {
     *           ------------------------------项目列表------------------------------------------------
     *              "projectList": [
     *                    {
     *                       "LOCATION_BIANMA": "0030101",                       --->地图编码(数据字典)
     *                       "LONGITUDE": "111",                                 --->经度
     *                       "PROJECT_URL": "http://192.168.199.133:8080",       --->项目公网地址
     *                       "LATITUDE": "222",                                  --->纬度
     *                       "PROJECT_NAME": "大浩浩",                            -->项目部名称
     *                       "PROJECT_ID": "c2db0562ce5f4bd9a3794c7c56fc59c4"    --->(主键忽略)
     *                    },
     *                    {
     *                       "LOCATION_BIANMA": "0030101",
     *                       "LONGITUDE": "111",
     *                       "PROJECT_URL": "http://192.168.199.133:8080",
     *                       "LATITUDE": "111",
     *                       "PROJECT_NAME": "小浩浩",
     *                       "PROJECT_ID": "f5fb8606d45e43dfb141d9c89b2522b2"
     *                    }
     *              ]
     *     ],
     *     "success": true,
     *     "code": "0000",
     *     "msg": null,
     *     "backUrl": null,
     *     "rows": [],
     *     "footer": []
     *     }
     */
    @RequestMapping(value = "/getProjectListByMap", method = RequestMethod.GET)
    @ResponseBody
    public HttpJsonResult<List<PageData>> getProjectListByMap(HttpServletRequest request) {
        HttpJsonResult<List<PageData>> jsonResult = new HttpJsonResult<List<PageData>>();
        try {
            PageData pd = this.getPageData(request);
            String uid = pd.getString("uid");
            PageData user = new PageData();
            user.put("USER_ID", uid);
            user = appuserService.findById(user);
            if (user == null) {
                jsonResult.setMessage("该用户不存在！");
                jsonResult.setCode(ErrorCode.USER_NOT_EXIST);
                return jsonResult;
            }
            String mapBianma = pd.getString("mapBianma");
            if (StringUtil.isEmpty(mapBianma, true)) {
                jsonResult.setMessage("您没传地图城市对应的编码");
                jsonResult.setCode(ErrorCode.PARAM_ERROR);
                return jsonResult;
            }
            List<PageData> proList = projectService.getProjectInfoByMapBianma(mapBianma);//根据地图编码查询项目信息
            jsonResult.setData(proList);
            return jsonResult;
        } catch (Exception e) {
            jsonResult.setSuccess(false);
            jsonResult.setMessage("查询异常：" + e);
            return jsonResult;
        }
    }



    /**
     * @throws null
     * @api {GET} /appProject/getProjectPano根据项目编码查全景图
     * @apiName getProjectListByMap
     * @apiGroup appProject
     *
     * @apiParam {String}
     * @apiParamExample {json} Request-Example
     * example
     *     {
     *     		"uid": "123456",                                            ----->uid
     *     		"projectId": "456414153456615"                              ----->对应项目id
     *     }
     *
     * @apiSuccess {Boolean} success true or false，表示请求是否成功
     * @apiUse code
     * @apiSuccessExample {json} Success-Response:
     *     {
     *     "message": null,
     *     "total": 0,
     *     "data": [
     *                   {
     *                         "STATUS": 0,                                              ----状态0 启用
     *                         "CREATE_USER_ID": "",
     *                         "RESOURCE_ID": "2d7222514dd6421bba375ca25241fc36",
     *                         "RECORD_ID": "599630481714452cbff456902d1794c4",
     *                         "CREATE_TIME": "2018-04-09 14:12:43",
     *                         "TYPE": 1,                                                -----type 1类型为图片
     *                         "URL": "/uploadFiles/uploadImgs/20180409/b050465c51b6466d8b5e714b2910bdc3.jpg"
     *                   },
     *                   {
     *                          "STATUS": 0,
     *                          "CREATE_USER_ID": "",
     *                          "RESOURCE_ID": "3200a77e6bd54870b94d3f3b4697555b",
     *                          "RECORD_ID": "599630481714452cbff456902d1794c4",
     *                          "CREATE_TIME": "2018-04-09 14:05:58",
     *                          "TYPE": 1,
     *                          "URL": "/uploadFiles/uploadImgs/20180409/848761229cb9424d84cbd3f3f1e85a49.jpg"
     *                   }
     *                   ],
     *     "success": true,
     *     "code": "0000",
     *     "msg": null,
     *     "backUrl": null,
     *     "rows": [],
     *     "footer": []
     *     }
     */
    @RequestMapping(value = "/getProjectPano", method = RequestMethod.GET)
    @ResponseBody
    public HttpJsonResult<List<PageData>> getProjectPano(HttpServletRequest request) {
        HttpJsonResult<List<PageData>> jsonResult = new HttpJsonResult<List<PageData>>();
        try {
            PageData pd = this.getPageData(request);
            String uid = pd.getString("uid");
            PageData user = new PageData();
            user.put("USER_ID", uid);
            user = appuserService.findById(user);
            if (user == null) {
                jsonResult.setMessage("该用户不存在！");
                jsonResult.setCode(ErrorCode.USER_NOT_EXIST);
                return jsonResult;
            }
            String projectId = pd.getString("projectId");
            if (StringUtil.isEmpty(projectId, true)) {
                jsonResult.setMessage("您没传项目id");
                jsonResult.setCode(ErrorCode.PARAM_ERROR);
                return jsonResult;
            }
            List<PageData> panoList = resourceService.getResourceByRecordId(projectId);
            jsonResult.setData(panoList);
            return jsonResult;
        } catch (Exception e) {
            jsonResult.setSuccess(false);
            jsonResult.setMessage("查询异常：" + e);
            return jsonResult;
        }
    }


    //测试连接其他服务器访问接口统计信息(测试用)
//    @RequestMapping(value = "/getTestInfo", method = RequestMethod.GET)
//    @ResponseBody
//    public HttpJsonResult<List<PageData>> getTestInfo(HttpServletRequest request) {
//        HttpJsonResult<List<PageData>> jsonResult = new HttpJsonResult<List<PageData>>();
//        try {
//            PageData pd = this.getPageData(request);
//            String uid = pd.getString("uid");
//            PageData user = new PageData();
//            user.put("USER_ID", uid);
//            user = appuserService.findById(user);
//            if (user == null) {
//                jsonResult.setMessage("该用户不存在！");
//                jsonResult.setCode(ErrorCode.USER_NOT_EXIST);
//                return jsonResult;
//            }
//            String mapBianma = pd.getString("mapBianma");
//            if (StringUtil.isEmpty(mapBianma, true)) {
//                jsonResult.setMessage("您没传地图城市对应的编码");
//                jsonResult.setCode(ErrorCode.PARAM_ERROR);
//                return jsonResult;
//            }
//            String result = projectinnterfacerelService.getTestInfo(mapBianma, "002");
//
//            jsonResult.setMessage(result);
//            return jsonResult;
//        } catch (Exception e) {
//            jsonResult.setSuccess(false);
//            jsonResult.setMessage("查询异常：" + e);
//            return jsonResult;
//        }
//
//    }


}
