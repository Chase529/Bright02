package com.fh.service.web.projectinnterfacerel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.fh.util.HttpGetUtil;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import com.fh.service.web.projectinnterfacerel.ProjectInnterfaceRelManager;

/**
 * 说明： 项目与接口的关联模块
 * 创建人：FH admin
 * 创建时间：2018-03-28
 */
@Service("projectinnterfacerelService")
public class ProjectInnterfaceRelService implements ProjectInnterfaceRelManager {

    @Resource(name = "daoSupport")
    private DaoSupport dao;

    /**
     * 新增
     *
     * @param pd
     * @throws Exception
     */
    public void save(PageData pd) throws Exception {
        dao.save("ProjectInnterfaceRelMapper.save", pd);
    }

    /**
     * 删除
     *
     * @param pd
     * @throws Exception
     */
    public void delete(PageData pd) throws Exception {
        dao.delete("ProjectInnterfaceRelMapper.delete", pd);
    }

    /**
     * 修改
     *
     * @param pd
     * @throws Exception
     */
    public void edit(PageData pd) throws Exception {
        dao.update("ProjectInnterfaceRelMapper.edit", pd);
    }

    /**
     * 列表
     *
     * @param page
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<PageData> list(Page page) throws Exception {
        return (List<PageData>) dao.findForList("ProjectInnterfaceRelMapper.datalistPage", page);
    }

    /**
     * 列表(全部)
     *
     * @param pd
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<PageData> listAll(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("ProjectInnterfaceRelMapper.listAll", pd);
    }

    /**
     * 通过id获取数据
     *
     * @param pd
     * @throws Exception
     */
    public PageData findById(PageData pd) throws Exception {
        return (PageData) dao.findForObject("ProjectInnterfaceRelMapper.findById", pd);
    }

    /**
     * 批量删除
     *
     * @param ArrayDATA_IDS
     * @throws Exception
     */
    public void deleteAll(String[] ArrayDATA_IDS) throws Exception {
        dao.delete("ProjectInnterfaceRelMapper.deleteAll", ArrayDATA_IDS);
    }

    @Override
    public List<PageData> getProjectInterfaceByCondition(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("ProjectInnterfaceRelMapper.getProjectInterfaceByCondition", pd);
    }


    /**
     * 根据地图地区对应编码,和接口编号，去汇总统计人员管理的数量
     */
    public int getPersonManagerCount(String mapBianma, String interfaceCode) throws Exception {
        PageData query = new PageData();
        query.put("mapBianma", mapBianma);
        query.put("interfaceCode", interfaceCode);
        List<PageData> urlResult = this.getProjectInterfaceByCondition(query);
        List<JSONObject> finalResult = this.forEachInterface(urlResult, null);
        int total = 0;
        for (int i = 0; i < finalResult.size(); i++) {
            String eachTotal = finalResult.get(i).getString("total");
            if (null != eachTotal) {
                total += Integer.parseInt(eachTotal);
            }
        }
        return total;
    }



    /**  ----------------**调用各个项目接口统计返回数据处理**---------------
     * 根据地图地区对应编码,和接口编号，去汇总统计人员管理，安全，质量等的数量
     *
     */
    public PageData getPersonManagerCount0(String mapBianma) throws Exception {
        PageData query = new PageData();
        query.put("mapBianma", mapBianma);
        List<PageData> urlResult = this.getProjectInterfaceByCondition(query);
        List<JSONObject> finalResult = this.forEachInterface(urlResult, null);
        PageData statistic = new PageData();
        //人员管理
        int depPersonCount = 0;
        int outCompanyCount = 0;
        int outStaffCount = 0;
        //质量管理
        int qualityCount = 0;
        int qualityNotCount = 0;
        int qualityHasCount = 0;
        int qualityDoCount = 0;
        //安全管理
        int securityCount = 0;
        int securityNotCount = 0;
        int securityHasCount = 0;
        int securityDoCount = 0;
        //环保管理
        int environmentCount = 60;
        int destroyCount = 28;
        int recoverCount = 32;
        //进度管理
        int sumProgress = 30;//总进度(%)
        int outputValue = 55;//总产值(亿元)

        for (int i = 0; i < finalResult.size(); i++) {//循环Json数组 统计各个接口的不同数据求总和
            JSONObject data = finalResult.get(i).getJSONObject("data");
            if (null != data) {//
                //人员
                depPersonCount += Integer.parseInt(data.getString("depPersonCount"));
                outCompanyCount += Integer.parseInt(data.getString("outCompanyCount"));
                outStaffCount += Integer.parseInt(data.getString("outStaffCount"));
                //质量
                qualityCount += Integer.parseInt(data.getString("qualityCount"));
                qualityNotCount += Integer.parseInt(data.getString("qualityNotCount"));
                qualityHasCount += Integer.parseInt(data.getString("qualityHasCount"));
                qualityDoCount += Integer.parseInt(data.getString("qualityDoCount"));
                //安全
                securityCount += Integer.parseInt(data.getString("securityCount"));
                securityNotCount += Integer.parseInt(data.getString("securityNotCount"));
                securityHasCount += Integer.parseInt(data.getString("securityHasCount"));
                securityDoCount += Integer.parseInt(data.getString("securityDoCount"));
                //TODO 环保预留,暂时使用假数据
//                environmentCount += Integer.parseInt(data.getString("environmentCount"));
//                destroyCount += Integer.parseInt(data.getString("destroyCount"));
//                recoverCount += Integer.parseInt(data.getString("recoverCount"));
                //TODO 进度
//                outputValue += Integer.parseInt(data.getString("outputValue"));
            }
        }
        //准备最后返回数据：
        //人员管理
        statistic.put("depPersonCount", depPersonCount);//项目部总人数
        statistic.put("outCompanyCount", outCompanyCount);//(外包公司)施工班组总数
        statistic.put("outStaffCount", outStaffCount);//(外包公司员工)施工班组人员总数
        //质量管理
        statistic.put("qualityCount", qualityCount);//质量总数
        statistic.put("qualityNotCount", qualityNotCount);//未完成
        statistic.put("qualityHasCount", qualityHasCount);//已完成
        statistic.put("qualityDoCount", qualityDoCount);//正在完成
        //安全管理
        statistic.put("securityCount", securityCount);//安全总数
        statistic.put("securityNotCount", securityNotCount);//未完成
        statistic.put("securityHasCount", securityHasCount);//已完成
        statistic.put("securityDoCount", securityDoCount);//正在完成
        //进度管理 TODO 按暂时使用假数据 30% 55
        statistic.put("sumProgress", sumProgress);//总进度
        statistic.put("outputValue", outputValue);//产值
        //环保管理 TODO 预留 暂用假数据(60,28,32)
        statistic.put("environmentCount", environmentCount);//环保监控点
        statistic.put("destroyCount", destroyCount);//已破坏监控点
        statistic.put("recoverCount", recoverCount);//正在恢复监控点
        return statistic;
    }


    /**
     * 循环访问线上项目接口获取数据,得到Json,返回Json数组 用于后续分别统计求和
     *
     * @param result
     * @return
     */
    public List<JSONObject> forEachInterface(List<PageData> result, Map<String, String> param) {
        List<JSONObject> finalResult = new ArrayList<JSONObject>();
        for (int i = 0; i < result.size(); i++) {
            JSONObject EachResult = null;
            String holeUrl = "";
            holeUrl += result.get(i).getString("project_url");
            holeUrl += result.get(i).getString("interface_value");
            System.err.println("----the get url is------>" + holeUrl);
            EachResult = HttpGetUtil.getJsonFromUrl(holeUrl, param);
            if (null != EachResult) {
                finalResult.add(EachResult);
            }
        }
        return finalResult;
    }


    /**
     * 测试用
     *
     * @return
     * @throws Exception
     */
    public String getTestInfo(String mapBianma, String interfaceCode) throws Exception {
        PageData query = new PageData();
        query.put("mapBianma", mapBianma);
        query.put("interfaceCode", interfaceCode);
        List<PageData> result = this.getProjectInterfaceByCondition(query);
        Map<String, String> p = new HashMap<String, String>();
        p.put("uid", "1");
        p.put("status", "0");
        if (null != this.forEachInterface(result, p)) {
            return this.forEachInterface(result, p).toString();
        }
        return "its null";
    }

}

