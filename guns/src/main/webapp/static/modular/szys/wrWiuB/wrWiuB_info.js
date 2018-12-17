/**
 * 初始化取用水户管理详情对话框
 */
var WrWiuBInfoDlg = {
    wrWiuBInfoData : {},
    adZtreeInstance : null,//行政区划
    validateFields: {
        nsrsbh: {
            validators: {
                notEmpty: {
                    message: '纳税人识别号不能为空'
                }
            }
        },
        wiuNm: {
            validators: {
                notEmpty: {
                    message: '取用水户全称不能为空'
                }
            }
        },
        adNm: {
            validators: {
                notEmpty: {
                    message: '归属行政层级不能为空'
                }
            }
        },

    }
};

/**
 * 清除数据
 */
WrWiuBInfoDlg.clearData = function() {
    this.wrWiuBInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WrWiuBInfoDlg.set = function(key, val) {
    this.wrWiuBInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WrWiuBInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WrWiuBInfoDlg.close = function() {
    parent.layer.close(window.parent.WrWiuB.layerIndex);
}

/**
 * 收集数据
 */
WrWiuBInfoDlg.collectData = function() {
    this
    .set('uuid')
    .set('djxh')
    .set('nsrsbh')
    .set('wiuNm')
    .set('lrNm')
    .set('lawAtt')
    .set('userTp')
    .set('tradTp')
    .set('isHiLev')
    .set('ecoTp')
    .set('monG')
    .set('wiuTp')
    .set('yrWu')
    .set('certSta')
    .set('isVimp')
    .set('adlCd')
    .set('sljgCd')
    .set('swjgCd')
    .set('web')
    .set('eMail')
    .set('addr')
    .set('zip')
    .set('tel')
    .set('fax')
    .set('isuse')
    .set('runSta')
    .set('ts')
    .set('lgtd')
    .set('lttd')
    .set('nt');
}

/**
 * 提交添加
 */
WrWiuBInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //验证
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wrWiuB/add", function(data){
        Feng.success("添加成功!");
        window.parent.WrWiuB.table.refresh();
        WrWiuBInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wrWiuBInfoData);
    ajax.start();
}

/**
 * 打开查看取用水户管理详情
 */
WrWiuBInfoDlg.openWrWiuBDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '取用水户管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wrWiuB/wrWiuB_update/' + WrWiuB.seItem.uuid
        });
        this.layerIndex = index;
    }
};

/**
 * 提交修改
 */
WrWiuBInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wrWiuB/update", function(data){
        Feng.success("修改成功!");
        window.parent.WrWiuB.table.refresh();
        WrWiuBInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wrWiuBInfoData);
    ajax.start();
}

/**
 * 验证数据是否为空
 */
WrWiuBInfoDlg.validate = function () {
    $('#wrWiuBInfo').data("bootstrapValidator").resetForm();
    $('#wrWiuBInfo').bootstrapValidator('validate');
    return $("#wrWiuBInfo").data('bootstrapValidator').isValid();
}

/****************************行政区划tree***************************************/

/**
 * 点击行政区划ztree列表的选项时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
WrWiuBInfoDlg.onClickAddv = function(e, treeId, treeNode) {
    $("#adNm").attr("value", WrWiuBInfoDlg.adZtreeInstance.getSelectedVal());
    $("#adlCd").attr("value", treeNode.id);
}

/**
 * 显示机构选择的树
 *
 * @returns
 */
WrWiuBInfoDlg.showAddvSelectTree = function() {
    var adNm = $("#adNm");
    var adNmOffset = $("#adNm").offset();
    $("#parentAddvMenu").css({
        left : adNmOffset.left + "px",
        top : adNmOffset.top + adNm.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDownAddv);
}

function onBodyDownAddv(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "parentAddvMenu" || $(
        event.target).parents("#parentAddvMenu").length > 0)) {
        WrWiuBInfoDlg.hideAddvSelectTree();
    }
}

/**
 * 隐藏机构选择的树
 */
WrWiuBInfoDlg.hideAddvSelectTree = function() {
    $("#parentAddvMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDownAddv);// mousedown当鼠标按下就可以触发，不用弹起
}

/****************************行政区划tree***************************************/

$(function() {

    //验证
    Feng.initValidator("wrWiuBInfo", WrWiuBInfoDlg.validateFields);

    //行政区划
    // var adZtree = new $ZTree("parentAddvMenuTree", "/common/adTree");
    // adZtree.bindOnClick(WrWiuBInfoDlg.onClickAddv);
    // adZtree.init();
    // WrWiuBInfoDlg.adZtreeInstance = adZtree;

});
