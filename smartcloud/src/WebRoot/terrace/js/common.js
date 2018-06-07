//服务器列表
var hostList = {
    //"debug": "http://localhost:8080/FHADMINM3",//本地服务器
    //"debug":"http://192.168.199.155:8081/FHADMINM3",
     "001": "http://shfawo.cn:8000",//内蒙 
     "002": "http://shfawo.cn:8080/lianghui",//两徽
     "003": "http://shfawo.cn:8080/babiao",//景中8标
     "004": "http://shfawo.cn:8080/jiubiao",//景中9标
     "005": "http://shfawo.cn:8082/guanlang",//管廊
     "006": "http://shfawo.cn:8082/weihai",//威海
     "007":	"http://shfawo.cn:8080/demo",//演示版本
     "008": "http://shfawo.cn:8080/sigongsi", //路桥四公司  
     "009": "http://shfawo.cn:8080/jqkj",//济青扩建六标四工区
    //"001": "http://shfawo.cn:8000",//内蒙 六处
    //"002": "http://shfawo.cn:8080/lianghui",//内蒙 一处
     "010": "http://192.168.199.165:8082/FHADMINM3/",//内蒙 
}

//项目信息（展示图，介绍等）
var projectsInfo = {
    "001": {
        "img": "imgs/billboard/noproj001",
        "desc": "本合同段路线起讫桩号为K24+800-K62+947,全长38.147km，主要工程内容为路基、路面、桥涵及排水防护工程。总投资1193798339元，工期30个月。开工日期：2015年11月20日，计划交工日期2018年5月。",
        "name":"内蒙东胜高速"
    },
    "002": {
        "img": "imgs/billboard/noproj002",
        "desc": "我项目部线路起点位于甘陕界杨店乡石马坪村，桩号K0+000，路线沿红崖河布设，在杨店乡设置了杨店互通、收费站及服务区，之后进入灵官峡沟道，路基工程终点位于果老隧道中间，桩号YK10+260（ZK10+240）总长10.26km；路面、机电工程终点桩号K33+210，总长33.21km；以及全线53.25km范围内的房建、绿化、交安工程，合同金额为20.39亿元。 <br/>&nbsp&nbsp;&nbsp&nbsp;&nbsp&nbsp;项目主线采用双向四车道高速公路标准，设计行车速度为80km/h，整体式路基宽度24.5m，分离式路基宽度12.25m，汽车荷载等级为公路-I级；路面采用高性能改性沥青混凝土4cm（superpave-13）+高性能改性沥青混凝土6cm（superpave-20）+密集配沥青碎石8cm（ATB-25）+热熔改性沥青碎石封层+水泥稳定碎石34cm+水泥稳定碎石18cm（桥隧部分路面结构：高性能改性沥青混凝土4cm（superpave-13）+高性能改性沥青混凝土6cm（superpave-20）的结构形式。",
        "name":"两徽"
    },
    "003": {
        "img": "imgs/billboard/noproj004",
        "desc": "本项目起点位于机场高速（S1）中川收费站管理所南1公里处，主匝道采用高架桥方式上跨马家山互通立交、中川城际铁路、迎宾大道、Z031公路后落地，终点位于机场T2航站楼南约0.8公里处，终点顺接中川机场迎宾大道，主匝道路线全长2.3公里，一般匝道长度3.859公里，共计6.159公里。",
        "name":"景中八标"
    },
    "004": {
        "img": "imgs/billboard/noproj005",
        "desc": "本项目起点位于机场高速（S1）中川收费站管理所南1公里处，主匝道采用高架桥方式上跨马家山互通立交、中川城际铁路、迎宾大道、Z031公路后落地，终点位于机场T2航站楼南约0.8公里处，终点顺接中川机场迎宾大道，主匝道路线全长2.3公里，一般匝道长度3.859公里，共计6.159公里。",
        "name":"景中九标",
    },
    "005": {
        "img": "imgs/billboard/noproj006",
        "desc":"",
        "name":"南昌管廊"
    },
    "006": {
        "img": "imgs/billboard/noproj007",
        "desc":"",
        "name":"威海改建工程"
    },
    "007": {
        "img": "imgs/billboard/noproj003",
        "desc":"",
        "name":"智慧工地" 
    },
    "008":{
        "img":"imgs/billboard/noproj008",
        "desc":"",
        "name":"路桥四公司"
    },
    "009":{
        "img":"imgs/billboard/noproj009",
        "desc":"济南至青岛高速公路改扩建工程起于青岛即墨市朱家官庄的新主线收费站，自东向西依次经过潍坊、淄博、滨州，经唐王枢纽互通立交与G35（济广高速公路）联结，终于济南零点立交，全线长309.2公里，其中青银高速段286.947公里，济广高速段22.210公里。既有高速公路为双向四车道，改扩建后采用高速公路标准，设计速度120公里/小时，采用“高速公路两侧拼宽为主、局部单侧或两侧分离加宽为辅”的加宽方式，标准路基宽度42米。济青高速公路在扩建期间，仍保持正常通行。项目计划工期42个月。本项目为济南至青岛高速公路改扩建工程第六合同段，主线全长51.175公里，起止桩号为G20 K294+385~K323+350、G35 K0+000~K22+210，G20 K323+350点与G35 K0+000点重合；其中章丘段全长24.62公里，起止桩号为G20 294+385~K319+000，历城段主线全长26.56公里，起止桩号为G20 K319+000~K323+350、G35 K0+000~K22+210。第六合同段设计有高架桥2座（华山高架桥、小许家高架桥）、特大桥1座（小清河特大桥）、大桥3座（巨野河大桥、绣江河大桥、漯河大桥），互通立交7处（华山互通立交、王舍人互通立交、小许家互通立交、临港互通立交、唐王枢纽互通立交、章丘西互通立交、章丘互通立交），中桥8座，小桥14座，匝道跨线桥6座，分离立交10座，通道67座，服务区1处（济南东），天桥4道，涵洞181道。主要工程数量：路基借土填方330万m3、挖土方71万m3、利用方54万m3、石灰土137万m3、水泥搅拌桩和CFG桩81.7万m；路面水稳混合料210万吨、沥青混凝土97万吨、水泥砼路面24万m2、路缘石23.4万m、冷再生混合料6万吨；桥涵钢筋12万吨、钢绞线1.3万吨、结构砼99万m3、桩基4204根（19.6万m）、预制梁板4388块、支座19626个、涵洞5187m；交安设施波形护栏23万m、标志标牌643块、标线12.6万m2。",
        "name":"济青改扩建六标七工区"
    }
}

var API_HOST_KEY = "host";
var PROJECT_CODE = "project_code";
var apiHost = function(){
    return appcan.locStorage.getVal(API_HOST_KEY);
};
var Common = {
    USER_ID: "userid",
    apiList: {
        "uploadFile": apiHost() + '/appPhoto/upload',//上传图片
        "faceCompare": apiHost() + '/appuser/faceCompare',//人脸比对
        "login": apiHost() + '/appuser/login',//用户登录
        "updatePwd": apiHost() + '/appuser/updatePwd',//更新密码
        "forgotPwd": apiHost() + '/appuser/forgotPwd',//忘记密码
        "getUserInfo": apiHost() + '/appuser/getUserInfo',//获取用户信息,
        "getProjectInfo":apiHost() + '/appProject/getLatestProjectData', //获取最新项目信息
        "sendVerifyCode": apiHost() + '/appuser/sendVerifyCode',//发送验证码
        "getPedestalList": apiHost() + '/appPedestal/getPedestalList',//查询台座列表
        "getBeamboardStat": apiHost() + '',//查询梁板统计数据
        "getPedetalDetail": apiHost() + '/appPedestal/getPedestalDetail',//查询台座详情
        "getBeamboardList": apiHost() + '/appBeamboard/getBeamboardList',//查询梁板列表
        "getBeamboardDetail": apiHost() + '/appBeamboard/getBeamboardDetail',//查询梁板详情
        "getBeamboardType": apiHost() + '/appDictionary/getBeamboardType',//查询梁板类型列表
        "beamboardSave": apiHost() + '/appBeamboard/save',//保存梁板
        "beamboardUpdate": apiHost() + '/appBeamboard/update',//更新梁板
        "getUsers": apiHost() + '/appuser/getAllUsersByDepartment',//查询用户列表
        "getAllUsers": apiHost() + '/appuser/getAllDepartmentUsers',//查询所有部门用户
        "getBeamareaList": apiHost() + '/appBeamarea/getBeamareaList',//查询存梁区列表
        "saveBeamboard": apiHost() + '/appBeamarea/save',//存梁
        "outBeamField": apiHost() + '/appBeamarea/outBeamField',//出场
        "getAccountData": apiHost() + '/appBeamboard/getAccountData',//查询梁板台账
        "getBeamareaBoardStat": apiHost() + '/appBeamarea/getBeamareaBoardStatistic',//梁板数据统计
        "getBeamStat": apiHost() + '/appBeamboard/getBeamFiledStatisticalData',//梁厂首页统计数据
        
        /*safe module*/
        "getSecurityplanList": apiHost() + '/appSecurityplan/getSecurityplanList',//查询安全预案列表
        "getSecurityplanDetail": apiHost() + '/appSecurityplan/getSecurityplanDetail',//查询安全预案详情
        "getSecurityTypeList": apiHost() + '/appSecurityplan/getSecurityTypeList',//查询安全预案类别列表
        "getInspectList": apiHost() + "/appInspect/getInspectList",//查询安全巡查列表,
        "inspectSave": apiHost() + "/appInspect/save",//新增安全巡查,
        "inspectUpdate": apiHost() + "/appInspect/update",//更新安全巡查记录,
        "getInspectDetail": apiHost() + "/appInspect/getInspectDetail",//查询安全巡查详情
        "getInspectData": apiHost() + '/appInspect/getInspectData',//查询复查/台账数据
        "getInspectRectificateData": apiHost() + '/appInspect/getInspectRectificateData',//查询整改/重新整改数据
        "getInspectNature": apiHost() + '/appDictionary/getInspectNature',//巡查性质查询
        "getDeparments": apiHost() + '/appDictionary/getDeparments',//查询部门
        "getStatisticalData": apiHost() + '/appDevice/getStatisticalData',//查询统计数据
        "getHiddenDangerNumber": apiHost() + '/appInspect/getHiddenDangerNumber',//查询安全隐患数量
        "getSafeArrangementList":apiHost() + '/appArrangement/appArrangementList', //获取安全交底（未交底和已交底）列表
        "getSafeArrangementDetail":apiHost() + '/appArrangement/getArrangementDetail',    //获取安全交底详情
        "saveSafeArrangement":apiHost() + '/appArrangement/submit', // 保存安全交底
        
        /*device module*/
        "getDeviceList": apiHost() + '/appDevice/getDeviceList',//查询设备列表
        "getDeviceSpecificate": apiHost() + '/appDictionary/getDeviceSpecificate',//查询设备规格
        "getDeviceNature": apiHost() + '/appDictionary/getDeviceNature',//查询设备性质
        "getDeviceType": apiHost() + '/appDictionary/getDeviceType',//查询设备类型
        "deviceSave": apiHost() + '/appDevice/save',//保存设备
        "deviceFaultSave": apiHost() + '/appDevice/failureSave',//故障设备添加
        "deviceExit": apiHost() + "/appDevice/deviceExit",//设备退场
        "oilSave": apiHost() + '/appAddoilRecord/save',//新增加油记录
        "getAddoilRecordList": apiHost() + '/appAddoilRecord/getAddoilRecordList',//查询加油记录
        "getDeviceAddoilRecordList": apiHost() + '/appAddoilRecord/getDeviceAddoilRecordList',//查询设备加油记录
        "getAllDeviceList": apiHost() + '/appDevice/getAllDeviceList',//查询所有设备
        "getDeviceInfo": apiHost() + '/appDevice/getDeviceDetail',//查询设备详情
        "getOilType": apiHost() + '/appDictionary/getOilType',//查询油品种类
        "getOilStatData": apiHost() + '/appAddoilRecord/getStatisticalData',//查询设备加油统计数据
        "getDeviceOperators": apiHost() + '/appOperator/getOpeatorList',//查询操作员列表
        "getDeviceStatNum": apiHost() + '/appDevice/getStatisticalDeviceCountByStatus',//查询设备统计数量
        "getAllOperatorList": apiHost() + '/appOperator/getAllOperatorList',//查询所有操作员列表
        "getWillOverduNum": apiHost() + '/appDevice/getDeviceInsAndLeaseOverduNum',//获取设备保险到期数量和租赁到期数量
        "getOperatorTypeList": apiHost() + '/appDictionary/getOperatorCardType',//查询操作证类型
        "getDevInsuranceList" :apiHost() + '/appDevInsurance/getDevInsuranceList',  //获取设备保险列表
        "getDeviceProjectList":apiHost() + '/appDeviceCare/getDeviceCareList', //获取设备维护列表 
        "saveDeviceInsure" :apiHost() + '/appDevInsurance/save', //保存设备保险
        "saveDeviceProtect":apiHost() + '/appDeviceCare/save', //保存设备维护
        "getDeviceMaintainType":apiHost() + '/appDictionary/getDeviceMaintainType',  //获取设备维护类型
        "operatorSave": apiHost() + '/appOperator/save',//保存操作员
        "statDeviceNum": apiHost() + '/appDevice/getDevicesStatistics',//设备数量统计
        
        "getcLiangdianDailyData": apiHost() + '/appBeamboard/getcLiangdianDailyData',//查询梁厂日报
        
        "checkingIn": apiHost() + '/appuser/checking',//提交考勤数据
        "getCheckingIn": apiHost() + '/appuser/checkingList',//查询考勤数据
        "sendMsg": apiHost() + '',//发送通知
        "getMsgDetail": apiHost() + '',//获取通知详情
        "getMsgList": apiHost() + '',//获取通知列表
        "getDailyCheckList": apiHost() + '/appSecurityCheck/getSecurityCheckList',//获取日常检查列表
        "saveDailyCheck": apiHost() + '/appSecurityCheck/save',//保存日常检查数据
        "getDailyCheckDetail": apiHost() + '/appSecurityCheck/getCheckDetail',//查询日常检查详情
        "getSafeMeetList": apiHost() + '/appSecurityMeet/getSecurityMeetList',//查询安全会议列表
        "saveSafeMeet": apiHost() + '/appSecurityMeet/save',//保存安全会议数据
        "getSafeMeetDetail": apiHost() + '/appSecurityMeet/getMeetDetail',//查询安全会议详情
        "getAllDepartmentUsers": apiHost() + '/appuser/getAllDepartmentUsers',//查询所有部门人员
        //Inspect module
        "getQualityInspectList":apiHost() + '/appQualityInspect/getInspectList', //获取巡查列表
        "qualityInspectSave":apiHost() + '/appQualityInspect/save' , //保存质量巡查信息
        "qualityInspectUpdate":apiHost() + '/appQualityInspect/update', //更新质量巡查信息
        "getQualityInspectNature":apiHost() + '/appDictionary/getQualityInspectNature' , //获取质量巡查性质
        "getQualityCheckNature" :apiHost() + '/appDictionary/getQualityCheckNature', //获取质量日常检查性质
        "getQualityInspectDetail":apiHost() + '/appQualityInspect/getInspectDetail', //获取巡查详情
        "getQualityInspectData":apiHost() + '/appQualityInspect/getInspectData', //获取（复查/台账）数据列表
        "getQualityInspectRectificateData":apiHost() + '/appQualityInspect/getInspectRectificateData', //获取（整改、重新整改的）数据 列表
        "getQualityProblemNumber":apiHost() + '/appQualityInspect/getHiddenDangerNumber', // 获取质量问题数量
        "getQualityLedgerList" :apiHost() + '/appQualityInspect/getInspectData',  //获取质检台账的数据列表
        "getQualityCheckList":apiHost() + '/appQualityCheck/getQualityCheckList',  //获取日常检查列表
        "getQualityCheckDetail":apiHost() + '/appQualityCheck/getCheckDetail', //获取日常检查详情
        "saveQualityCheck":apiHost() + '/appQualityCheck/save', //保存检查信息
        "getQualityMeetList" :apiHost() + '/appQualityMeet/getQualityMeetList',   //获取质量会议列表
        "getQualityMeetDetail":apiHost() + '/appQualityMeet/getMeetDetail', //获取质量会议详情
        "saveQualityMeet":apiHost() + '/appQualityMeet/save', //保存质量会议信息 
        
        //Materials module
        "getLoadometerList": apiHost() + '/appStorageRecord/getStorageRecordList',//地磅数据
        //"getMaterialsStatData": apiHost() + '/',//查询物资管理入库统计
        "getMaterialSubjects": apiHost() + '/appDictionary/getMaterialSubjects',//查询物资科目
        "getMaterialsStoreList": apiHost() + "/appWarehouse/getWarehouses",//查询物资仓库列表
        "getMaterialsInputList": apiHost() + "/appStorageRecord/getStorageRecordList",//查询原材料台账
        "getMaterialsList": apiHost() + "/appMaterialCategory/getMaterialCategories",//查询材料列表
        "getMaterialSpecInfo": apiHost() + "/appMaterial/getMaterialByCategoryAndSpecification",//查询规格信息
        "getMaterialsSpecList": apiHost() + "/appMaterialCategory/getMaterialSpecificationList",//根据材料查询规格列表
        "materialsInput": apiHost() + "/appStorageRecord/save",//物资入库
        "materialsOutput": apiHost() + "/appPicking/save",//物资出库
        "getMaterialUseParts": apiHost() + "/",//查询使用部位
        "getMaterialsAccountList": apiHost() + "/appStorageRecord/getRawMaterialsList",//查询原材料
        "getMaterialsUseList": apiHost() + "/appPicking/getPickingRecordList",//查询物资出库记录
        "getMaterialsUseAccount": apiHost() + "/appPicking/getPickingRecordAccountList",//获取领料
        "getMaterialsUseAccountDetail": apiHost() + "/appPicking/getPickingRecordAccountDetail",//查询物资出库台账
        "getWeighbridgeStatData": apiHost() + "/appStorageRecord/getOverallStatistic", //获取地磅数据汇总信息
        "getWeighbridgeLineData": apiHost() + "/appStorageRecord/getMonthStatistic",//获取地磅采集物资趋势数据
        "getWeighbridgePieData": apiHost() + "/appStorageRecord/getStorageDataStatistic",//根据月份获取材料进场数据
        "getMaterialStockList": apiHost() + "/appMaterial/getMaterialInventorList",//查询材料库存列表
        "getWarnStockMaterialList": apiHost() + "/appMaterial/getWarnMaterialList",//查询预警材料列表
        "getMixStationStatData": apiHost() + "/",//查询拌合站生产汇总数据
        "getMixStationPieData": apiHost() + "/",//获取拌合站生产统计数据
        "getProduceRecordList": apiHost() + "/",//查询拌合站生产记录
        "getRecordDetail": apiHost() + "/",//查询拌合站生产记录详情
        "getProduceRecordStat": apiHost() + "/",//查询拌合站生产统计数据
        
        //personnel module
        "getDeparmentStatistics":apiHost() + '/appDeparment/getDeparmentStatistics', //获取部门上班人数和请假人数
        "getOutCompanyDeparmentStatistics" :apiHost() + '/appOutComStaff/getOutCompanyDeparmentStatistics', //获取外包公司上班人数和请假人数
        "getAppUserNumberByDeparment":apiHost() + '/appDeparment/getAppUserNumberByDeparment',  //获取部门和部门人数
        "getAppUsersByDeparment":apiHost() + '/appDeparment/getAppUsersByDeparment', //获取部门员工信息
        "getOutCompModList":apiHost() + '/appOutCompany/appOutComStaff/getOutCompModList', //获取外包公司员工列表
        "getStaffNumberByCompany":apiHost() + '/appOutCompany/getStaffNumberByCompany',  //获取外包公司名和外包公司人数
        "getStaffsByCompany":apiHost() + '/appOutCompany/getStaffsByCompany', //获取外包公司员工
        "getOutCompanyStaffInfo":apiHost()+'/appOutComStaff/getStaffInfo',  //获取外包公司员工信息
        "getLeaveType" :apiHost() + '/appDictionary/getLeaveType',  //获取请假类型
        "getMyLeaveStatistics":apiHost() + '/appLeave/getMyLeaveStatistics',  //请假统计（待我审批和我发起的）
        "getLeaveRecordList" :apiHost() + '/appLeave/getLeaveRecordList',  //获取请假记录
        "saveLeave":apiHost() + '/appLeave/save',  //保存请假信息
        "getPendingLeaveList" :apiHost() + '/appLeave/getPendingLeaveList', //获取待审批请假列表
        "approveLeave":apiHost() + '/appLeave/approveLeave',   //审批请假信息
        "getLeaveDetail":apiHost() + '/appLeave/getLeaveDetail', //获取请假详情
        "checkAttendance" :apiHost() + '/appOutComStaff/checkAttendance',     //负责人考勤打卡
        "getInsuranceInfo":apiHost() + '/appStaffInsurance/getInsuranceInfo',        //获取员工保险信息
        
        //Video module
        "getVideo" :apiHost() + '/appVideo/getVideo',          //获取影像资料
        "getInnovateData":apiHost() + '/appTechnologyInnovation/getTechnologyInnovationList',    //获取技术创新数据
        "getInnovateDetail": apiHost() + '/appTechnologyInnovation/detail',//查询技术创新
        "getRegion":apiHost() +'/appDictionary/getRegionName',  //获取工作分区
        "getVideList":apiHost()+'/appVideo/getVideoList',  //获取视频和图片列表
        "getPicListOfRegion":apiHost()+'/appVideo/getPicListOfRegion', //按分区获取
        
        //Schedule module
        "getScheduleDailyList": apiHost() + "/appDailyWork/getUnitProjectDailyWorkList",//查询日常工作列表
        "getScheduleDailyDetail": apiHost() + "/appDailyWork/getDailyWorkDetail",//查询日常工作详情
        "getProjectList" : apiHost() + "/appSchedule/getUnitProjectList",//查询工程分类
        "getAllProjectList": apiHost() + "/appSchedule/getDailyWorkProjectCategoryList",//查询所有工程数据
        "saveScheduleDaily": apiHost() + "/appDailyWork/save",//保存日常工作
        "updateScheduleDaily": apiHost() + "/appDailyWork/update",//更新日常工作
        "submitProjectDetailOrder": apiHost() + "/appDailyWork/listSubmit",//工程分解清单提交
        "getProjectDetailList": apiHost() + "/appDailyWork/getListingsList",//根据分项工程查询分解清单列表
        "getWorkPlanStat": apiHost() + "/appSchedule/getScheduleStatistical",//查询施工计划汇总数据
        "getWorkPlanList": apiHost() + "/appSchedule/getConstructionPlanStaticalList",//查询工程分类下施工计划详细列表
        "getWorkProgress": apiHost() + "/appSchedule/getProjectTypeStatisticsByCategory",//查询施工进度
        "getWorkProgressDetail": apiHost() + "/appSchedule/getUnitProjectStatisticsByTypeAndProject",//查询工程分类下各工程类型明细数据
        "getProjectCatProgress": apiHost() + "/",//查询施工计划产值完成情况列表
        "getNewestProgress": apiHost() + "/appSchedule/getProgressKanbanList",//查询最新进度看板数据
        "getProjectTotalProgressList": apiHost() + "/appSchedule/getSubProjectStaticalList",//查询工程总览数据
        "getBoxBeamType": apiHost() + "/appDictionary/getBoxBeamCategory",//查询箱梁类别列表
        "getSubProjectsByCondition": apiHost() + "/appSchedule/getSubProjectsByCategoryAndProject",//查询分项工程列表
        "getHomeCarouselList":apiHost() + "/appHomeCarousel/getHomeCarouselList",//查询轮播图
         "getFirm":apiHost() + "/appLeaseCompany/getleaseCompanyList",//获取租赁公司
        //HomeCarouselList module
        "getHomeCarouselList":apiHost() + "/appHomeCarousel/getHomeCarouselList",//查询轮播图
         "getFirm":apiHost() + "/appLeaseCompany/getleaseCompanyList",//获取租赁公司
         
         //Approval module
         "findMyApplicant":apiHost() + '/appApproval/findMyApplicant',  //查询我发起的
         "findCopyToMe":apiHost() + '/appApproval/findCopyToMe',  //查询抄送我的
         "getAllApprovalTypes":apiHost() + '/appDictionary/getAllApprovalTypes',  //查询审查类型
         
    },
    SUCCESS: "0000",
    errorMsg: {
        "0000": "",
        "0001": "系统错误",
        "0002": "参数错误",
        "0003": "保存失败",
        "0004": "编辑失败",
        "0005": "删除失败",
        "0006": "短信发送失败，请稍后重试",
        "0101": "用户名或密码错误",
        "0102": "用户名或密码错误",
        "0103": "用户不存在",
        "0104": "旧密码不正确",
        "0105": "新密码格式不正确",
        "0106": "手机号码错误",
        "0107": "短信验证码不正确",
        "0301": "梁板已经存在",
        "0302": "梁板code不存在",
        "0303": "梁板不存在",
        "0801": "您已签到，不能重复签到",
        "0802": "您已签退，不能重复签退"
    },
    regex: {
        password: /^[0-9a-zA-Z]{6,8}$/
    },
    util: {
        //是否空字符串
        isNullStr: function(str) {
            if(str == null || str == "") 
                return true;
            else
                return false;
        },
        //是否数字
        isNumber: function(str) {
            var reg = /^\d+$/;
            if(reg.test(str)) 
                return true;
            else 
                return false;
        },
        //是否价格
        isPrice: function(str) {
            var reg = /^\d+(.\d{1,2}){0,1}$/;
            if(reg.test(str)) 
                return true;
            else
                return false;
        },
        getCurDate: function() {
            var date = new Date();
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var day = date.getDate();
            
            return year + "-" + (month<10?("0"+month):month) + "-" + (day<10?(day):day);
        }
    },
    ajaxRequest: function(options) {
      var $self = this;
      //默认配置
      var defaultOptions = {
            url: options.url || "",
            type: options.type || 'POST',
            dataType: options.dataType || 'json',
            //contentType: options.contentType || 'application/json;charset=utf-8',
            data: options.data || {},
            //cache: options.cache || true,
            traditional: options.traditional || false,
            async: options.async!=undefined?options.async:true,
            timeout: 60000,
            beforeSend: options.before || function(xhr, settings) {
              console.log(defaultOptions.url);
              console.log(defaultOptions.data);
              appcan.window.openToast("正在加载...", -1, 5, 1);
            },
            success: function(rdata) {
                console.log("****************************");
                console.log(defaultOptions.url);
                console.log(defaultOptions.data);
                console.log(rdata); 
                console.log("****************************");
                
                if(rdata.code == $self.SUCCESS) {
                    if(options.success) {
                        options.success(rdata);
                    }
                }else if(rdata.code == 1 || rdata.code == "0103") {//未登录或用户不存在,跳转至登录页面
//                 $self.goToLogin();
				console.log("跳转至登陆页面")
                }else {
                	console.log(rdata.message)
                    if(rdata.message) {
                        $self.alert(rdata.message);
                    } else {
                        $self.alert($self.errorMsg[rdata.code]);
                    }
                }
                appcan.window.closeToast();
            },
            error: function(xhr, ts, error) {
                appcan.window.closeToast();
                if(ts == "timeout") {
                    appcan.alert("提示", "网络异常，请稍后重试");
                } else {
                    console.log(ts + " " + error);
                }
            },
            complete:function(xhr, status){
                
                if($(".button.disabled").length > 0) {
                    $(".button.disabled").removeClass("disabled");
                }
                
                if($("input[type=file]").length > 0) {
                    $("#uploadFile").val("");
                }
            }
      };
      
      if(options.contentType) {
          defaultOptions.contentType = 'application/json;charset=utf-8';
      }
      
      appcan.ajax(defaultOptions);  
    },
    tip: function(msg) {
        appcan.window.closeToast();
        appcan.window.openToast(msg, 2000, 5);
    },
    alert: function(msg, callback, title) {
        appcan.window.alert({
              title : title || "提示",
              content : msg || "",
              buttons : ['确定'],
              callback : callback || function(err, data, dataType, optId) {}
        });
    },
    confirm: function(msg, callback, title) {
        appcan.window.alert({
              title : title || "提示",
              content : msg || "",
              buttons : ['确定', '取消'],
              callback : callback || function(err, data, dataType, optId) {}
        });
    },
    goBack: function() {
        appcan.window.close(-1);
//    history.back();
    },
    goToLogin: function() {
        appcan.window.open({
            name: "login",
            dataType: 0,
            data: "./login.html",
            type: 0,
            aniId: 2
        });
    },
    setSlidingPage: function(enable) {
        if(uexWindow.setSlidingWindowEnabled) {
            uexWindow.setReportKey(0,1);//阻止返回键触发
            if(enable == 1 || enable == true) {
                uexWindow.setSlidingWindowEnabled(0);
            } else {
                uexWindow.setSlidingWindowEnabled(1);
                uexWIndow.onKeyPressed = function(data) {
                  if(data == 0) {
                    appcan.window.close(-1);
                  }  
                };
            }
        }
     },
     //右滑关闭窗口
     swipeRightClose: function() {
        var name = uexWidgetOne.platformName;
        
        if(name == "android" || name == "iOS") {
            uexWindow.setSlidingWindowEnabled(1);
            //uexWindow.setIsSupportSwipeCallback(JSON.stringify({isSupport:true}));
            uexWindow.onSwipeRight = function(){
                appcan.window.close(-1);
            };
        }
        
     },
     /** type 1.年月日  2.年月
      *  target 1.text 2.input
      */
     initUexWindowDate: function(type, targetType, callback){
        if(uexControl) {//初始化原生日历选择器
            appcan.button(".date-control", "", function(){
                var date = (targetType==1)?$(this).find('.date-text').text():$(this).val();
                $(this).attr("data-state", "active");
                var dateArr = date.split("-");
                if(type == 1) {
                    uexControl.openDatePicker(dateArr[0],dateArr[1],dateArr[2]);
                } else if(type == 2) {
                    uexControl.openDatePickerWithoutDay(dateArr[0],dateArr[1]);
                }
            });
            
            if(type == 1) {
                //日期选择回调函数
                uexControl.cbOpenDatePicker = function(opId, dataType, data) {
                    var $target = $('.date-control[data-state=active]');
                    switch(dataType) {
                        case 1:
                            var data = eval("("+data+")");
                            var year = parseInt(data.year);
                            var month = parseInt(data.month);
                            var day = parseInt(data.day);
                            var dateStr = year+"-"+(month<10?("0"+month):month)+"-"+(day<10?("0"+day):day);
                            if(targetType == 1) {
                                $target.find('.date-text').text(dateStr);
                            } else {
                                $target.val(dateStr);
                            }
                            
                            $target.removeAttr("data-state");
                            if(callback) {
                                callback();
                            }
                            break;
                        default: 
                            break;
                    }
                };
            } else if(type == 2) {
                //日期选择回调函数
                uexControl.cbOpenDatePickerWithoutDay = function(opId, dataType, data) {
                    var $target = $('.date-control[data-state=active]');
                    switch(dataType) {
                        case 1:
                            var data = eval("("+data+")");
                            var year = parseInt(data.year);
                            var month = parseInt(data.month);
                            var dateStr = year+"-"+(month<10?("0"+month):month);
                            if(targetType == 1) {
                                $target.find('.date-text').text(dateStr);
                            } else {
                                $target.val(dateStr);
                            }
                            
                            $target.removeAttr("data-state");
                            if(callback) {
                                callback();
                            }
                            break;
                        default: 
                            break;
                    }
                };
            }
        }
    },
    /**
      *  target 1.text 2.input
      */
     initUexWindowTime: function(targetType, callback){
        if(uexControl) {//初始化原生时间选择器
            appcan.button(".time-control", "", function(){
                var date = (targetType==1)?$(this).find('.date-text').text():($(this).val()||"00:00");
                $(this).attr("data-state", "active");
                var dateArr = date.split(":");
                uexControl.openTimePicker(dateArr[0],dateArr[1]);
            });
            
            //日期选择回调函数
            uexControl.cbOpenTimePicker = function(opId, dataType, data) {
                var $target = $('.time-control[data-state=active]');
                switch(dataType) {
                    case 1:
                        var data = eval("("+data+")");
                        var dateStr = data.hour+":"+data.minute;
                        if(targetType == 1) {
                            $target.find('.date-text').text(dateStr);
                        } else {
                            $target.val(dateStr);
                        }
                        
                        $target.removeAttr("data-state");
                        if(callback) {
                            callback();
                        }
                        break;
                    default: 
                        break;
                }
            };
        }
    },
     //获取定位坐标
     getLocation: function(callback) {
        var $self = this;
        
        if(uexLocation && uexLocation.openLocation) {
            //采用百度坐标系，获取当前定位地址
            uexLocation.openLocation("bd09", function(error) {
                if(error) {
                    //关闭定位
                    //uexLocation.closeLocation();
                    console.log("定位失败,请检查是否已打开定位功能");
                    return;
                }
            });
            
            uexLocation.onChange = function(lat, log) {
                //获取经纬度,获取地址 log：纬度 ，lat:经度
                if(lat && log) {
                    
                    //回调返回定位坐标
                    callback(lat, log);
                    //关闭定位
                    uexLocation.closeLocation();
                }
            }
        } else {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function(position) {
                   callback(position.coords.latitude, position.coords.longitude);
                });
            } else {
                Common.alert("定位失败");
            }
        }
    },
    //单图上传，使用jquery from异步上传
    /**
     * 需要先引用jquery.js与jquery.form.js,同时支持APP与webapp 
     *
     * fileInput 上传控件ID
     * form 上传表单ID
     * callback 上传成功回调函数，参数（图片相对路径）
     */
    singlePicUpload: function(fileInput, form, callback) {
       var $self = this;
       $(fileInput).on("change", function() {
       jQuery(form).ajaxSubmit({
          type: "POST",
          dataType: 'json',
          data: {uid: appcan.locStorage.getVal($self.USER_ID)},
          url: $self.apiList.uploadFile,
          beforeSend: function() {
              appcan.window.openToast("正在上传...", -1, 5, 1);
          },
          success: function(rdata) {
               if(rdata.code == Common.SUCCESS) {
                    if(callback) {
                        callback(rdata.data);
                    }
                } else if(rdata.code == 1 || rdata.code == "0103") {//未登录或用户不存在,跳转至登录页面
//                  $self.goToLogin();
					alert("请重新登陆")
                } else {
                    $self.alert(Common.errorMsg[rdata.code]);
                }
          },
          error: function(xhr, ts, error) {
                if(ts == "timeout") {
                    appcan.alert("提示", "网络异常，请稍后重试");
                } else {
                    appcan.alert("提示", ts+error);
                }
          },
          complete: function() {
                appcan.window.closeToast();
                $(fileInput).val("");//重置上传控件
          }
       });
    });
    },
    /*
     * userId 比对人ID
     * pic 比对图片地址
     **/
    faceCompare: function(userId, pic, callback) {
        var $self = this;
        $self.ajaxRequest({
           url: $self.apiList.faceCompare,
           data: {'uid': userId, 'pic': pic},
           success: function(rdata) {
               if(callback) {
                   callback(rdata.data);//返回比对结果
               }
           }
        });
    }
    
        
};
