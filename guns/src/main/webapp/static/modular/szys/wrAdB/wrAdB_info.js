/**
 * 初始化行政区划管理详情对话框
 */
var WrAdBInfoDlg = {
    wrAdBInfoData : {}
};

/**
 * 清除数据
 */
WrAdBInfoDlg.clearData = function() {
    this.wrAdBInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WrAdBInfoDlg.set = function(key, val) {
    this.wrAdBInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WrAdBInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WrAdBInfoDlg.close = function() {
    parent.layer.close(window.parent.WrAdB.layerIndex);
}

/**
 * 收集数据
 */
WrAdBInfoDlg.collectData = function() {
    this
    .set('adCd')
    .set('adNm')
    .set('adShnm')
    .set('adA')
    .set('isEnable')
    .set('ts')
    .set('nt');
}

/**
 * 提交添加
 */
WrAdBInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wrAdB/add", function(data){
        Feng.success("添加成功!");
        window.parent.WrAdB.table.refresh();
        WrAdBInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wrAdBInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
WrAdBInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wrAdB/update", function(data){
        Feng.success("修改成功!");
        window.parent.WrAdB.table.refresh();
        WrAdBInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wrAdBInfoData);
    ajax.start();
}

$(function() {

});
