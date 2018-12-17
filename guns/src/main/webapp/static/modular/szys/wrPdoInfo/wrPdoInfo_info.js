/**
 * 初始化取水口信息表详情对话框
 */
var WrPdoInfoInfoDlg = {
    wrPdoInfoInfoData : {},
    validateFields: {
        sourTp: {
            validators: {
                notEmpty: {
                    message: '水源类型不能为空'
                },

            }
        },
        sn: {
            validators: {
                stringLength: {
                    min: 0,
                    max: 3,
                    message: '长度必须在0到3位之间'
                },
            }
        },
        nt: {
            validators: {
                stringLength: {
                    min: 0,
                    max: 255,
                    message: '长度必须在0到255位之间'
                },
            }
        },
        wiuCd: {
            validators: {
                notEmpty: {
                    message: '取水用户编码不能为空'
                }
            }
        },
        intNm: {
            validators: {
                notEmpty: {
                    message: '取水口名称不能为空'
                }
            }
        },
        state: {
            validators: {
                notEmpty: {
                    message: '水源状态不能为空'
                }
            }
        },
        adNm: {
            validators: {
                notEmpty: {
                    message: '行政分区不能为空'
                }
            }
        },
    }
};

/**
 * 清除数据
 */
WrPdoInfoInfoDlg.clearData = function() {
    this.wrPdoInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WrPdoInfoInfoDlg.set = function(key, val) {
    this.wrPdoInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WrPdoInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WrPdoInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.WrPdoInfo.layerIndex);
}

/**
 * 收集数据
 */
WrPdoInfoInfoDlg.collectData = function() {
    this
    .set('uuid')
    .set('sn')
    .set('intNm')
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
    .set('ts')
    .set('state')
    .set('nt');
}

/**
 * 验证数据是否为空
 */
WrPdoInfoInfoDlg.validate = function () {
    $('#wrPdoInfo').data("bootstrapValidator").resetForm();
    $('#wrPdoInfo').bootstrapValidator('validate');
    return $("#wrPdoInfo").data('bootstrapValidator').isValid();
}


/**
 * 提交添加
 */
WrPdoInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    //验证
    if (!this.validate()) {

        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wrPdoInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.WrPdoInfo.table.refresh();
        WrPdoInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wrPdoInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
WrPdoInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //验证
    if (!this.validate()) {

        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wrPdoInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.WrPdoInfo.table.refresh();
        WrPdoInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wrPdoInfoInfoData);
    ajax.start();
}

/**
 * 隐藏机构选择的树
 */
WrPdoInfoInfoDlg.hideAddvSelectTree = function() {
    $("#parentAddvMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDownAddv);// mousedown当鼠标按下就可以触发，不用弹起
}

function onBodyDownAddv(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "parentAddvMenu" || $(
            event.target).parents("#parentAddvMenu").length > 0)) {
        WrPdoInfoInfoDlg.hideAddvSelectTree();
    }
}
/**
 * 点击行政区划ztree列表的选项时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
WrPdoInfoInfoDlg.onClickAddv = function(e, treeId, treeNode) {
    $("#adNm").attr("value", WrPdoInfoInfoDlg.adZtreeInstance.getSelectedVal());
    $("#adCd").attr("value", treeNode.id);
}
/**
 * 显示机构选择的树
 *
 * @returns
 */
WrPdoInfoInfoDlg.showAddvSelectTree = function() {
    var adNm = $("#adNm");
    var adNmOffset = $("#adNm").offset();
    $("#parentAddvMenu").css({
        left : adNmOffset.left + "px",
        top : adNmOffset.top + adNm.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDownAddv);
}


$(function() {
    //验证
    Feng.initValidator("wrPdoInfo", WrPdoInfoInfoDlg.validateFields);
    //行政区划
    var adZtree = new $ZTree("parentAddvMenuTree", "/common/adTree");
    adZtree.bindOnClick(WrPdoInfoInfoDlg.onClickAddv);
    adZtree.init();
    WrPdoInfoInfoDlg.adZtreeInstance = adZtree;
});
