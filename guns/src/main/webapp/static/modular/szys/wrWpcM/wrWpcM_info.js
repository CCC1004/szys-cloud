/**
 * 初始化取水口信息表详情对话框
 */
var WrWpcMInfoDlg = {
    wrWpcMInfoData : {},
    validateFields: {
        /*      sourTp: {
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
              },*/
        wrpNm: {
            validators: {
                notEmpty: {
                    message: '取水权人名称不能为空'
                }
            }
        },
        wpcSn: {
            validators: {
                notEmpty: {
                    message: '取水许可证不能为空'
                }
            }
        },
        adNm: {
            validators: {
                notEmpty: {
                    message: '取水权人名称不能为空'
                }
            }
        },
        wiuCd: {
            validators: {
                notEmpty: {
                    message: '取水许可证不能为空'
                }
            }
        }

    }
};

/**
 * 清除数据
 */
WrWpcMInfoDlg.clearData = function() {
    this.wrWpcMInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WrWpcMInfoDlg.set = function(key, val) {
    this.wrWpcMInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WrWpcMInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WrWpcMInfoDlg.close = function() {
    parent.layer.close(window.parent.WrWpcM.layerIndex);
}

/**
 * 收集数据
 */
WrWpcMInfoDlg.collectData = function() {
    this
        .set('wpcCd')
        .set('wpcSn')
        .set('wrpNm')
        .set('adCd')
        .set('wiuCd')
        .set('intBd')
        .set('intDl')
        .set('manOrgCd')
        .set('certCond')
        .set('certPrtCd')
        .set('drawDt')
        .set('drawStaff')
        .set('ts')
        .set('nt');
}

/**
 * 提交添加
 */
WrWpcMInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();
    //验证
    if (!this.validate()) {

        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wrWpcM/add", function(data){
        Feng.success("添加成功!");
        window.parent.WrWpcM.table.refresh();
        WrWpcMInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wrWpcMInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
WrWpcMInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectData();

    //验证
    if (!this.validate()) {

        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wrWpcM/update", function(data){
        Feng.success("修改成功!");
        window.parent.WrWpcM.table.refresh();
        WrWpcMInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wrWpcMInfoData);
    ajax.start();
}

/****************************行政区划tree***************************************/
/**
 * 隐藏机构选择的树
 */
WrWpcMInfoDlg.hideAddvSelectTree = function() {
    $("#parentAddvMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDownAddv);// mousedown当鼠标按下就可以触发，不用弹起
}

function onBodyDownAddv(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "parentAddvMenu" || $(
            event.target).parents("#parentAddvMenu").length > 0)) {
        WrWpcMInfoDlg.hideAddvSelectTree();
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
WrWpcMInfoDlg.onClickAddv = function(e, treeId, treeNode) {
    $("#adNm").attr("value", WrWpcMInfoDlg.adZtreeInstance.getSelectedVal());
    $("#adCd").attr("value", treeNode.id);
}
/**
 * 显示机构选择的树
 *
 * @returns
 */
WrWpcMInfoDlg.showAddvSelectTree = function() {
    var adNm = $("#adNm");
    var adNmOffset = $("#adNm").offset();
    $("#parentAddvMenu").css({
        left : adNmOffset.left + "px",
        top : adNmOffset.top + adNm.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDownAddv);
}
/****************************行政区划tree***************************************/


/**********************************校验**************************************/


/**
 * 验证数据是否为空
 */
WrWpcMInfoDlg.validate = function () {
    $('#wrWpcM').data("bootstrapValidator").resetForm();
    $('#wrWpcM').bootstrapValidator('validate');
    return $("#wrWpcM").data('bootstrapValidator').isValid();
}


/**********************************校验结束**************************************/
$(function() {
    //验证
    Feng.initValidator("wrWpcM", WrWpcMInfoDlg.validateFields);

    //行政区划
    var adZtree = new $ZTree("parentAddvMenuTree", "/common/adTree");
    adZtree.bindOnClick(WrWpcMInfoDlg.onClickAddv);
    adZtree.init();
    WrWpcMInfoDlg.adZtreeInstance = adZtree;
});
