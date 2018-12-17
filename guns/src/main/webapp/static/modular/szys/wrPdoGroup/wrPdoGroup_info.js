/**
 * 初始化水源分组详情对话框
 */
var WrPdoGroupInfoDlg = {
    wrPdoGroupInfoData : {}
};

/**
 * 清除数据
 */
WrPdoGroupInfoDlg.clearData = function() {
    this.wrPdoGroupInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WrPdoGroupInfoDlg.set = function(key, val) {
    this.wrPdoGroupInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WrPdoGroupInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WrPdoGroupInfoDlg.close = function() {
    parent.layer.close(window.parent.WrPdoGroup.layerIndex);
}

/**
 * 收集数据
 */
WrPdoGroupInfoDlg.collectData = function() {
    this
    .set('uuid')
    .set('sn')
    .set('groupNm')
    .set('wpcCd')
    .set('wpcSn')
    .set('intBd')
    .set('intDl')
    .set('wiuCd')
    .set('sourTp')
    .set('intUse')
    .set('tradTp')
    .set('gwoaTp')
    .set('isPipe')
    .set('wrtrTp')
    .set('adCd')
    .set('wrzCd')
    .set('addr')
    .set('intTp')
    .set('groTp')
    .set('lgtd')
    .set('lttd')
    .set('state')
    .set('nt');
}

/**
 * 提交添加
 */
WrPdoGroupInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wrPdoGroup/add", function(data){
        Feng.success("添加成功!");
        window.parent.WrPdoGroup.table.refresh();
        WrPdoGroupInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wrPdoGroupInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
WrPdoGroupInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wrPdoGroup/update", function(data){
        Feng.success("修改成功!");
        window.parent.WrPdoGroup.table.refresh();
        WrPdoGroupInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wrPdoGroupInfoData);
    ajax.start();
}

$(function() {

});
