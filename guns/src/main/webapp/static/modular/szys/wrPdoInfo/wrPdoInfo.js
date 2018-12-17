/**
 * 取水口信息表管理初始化
 */
var WrPdoInfo = {
    id: "WrPdoInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
WrPdoInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '取水口代码', field: 'uuid', visible: false, align: 'center', valign: 'middle'},
        // {title: '序号', field: 'sn', visible: true, align: 'center', valign: 'middle'},
        {title: '取水口名称', field: 'intNm', visible: true, align: 'center', valign: 'middle'},
        // {title: '取水口许可证编码', field: 'wpcCd', visible: true, align: 'center', valign: 'middle'},
        {title: '取水许可证号', field: 'wpcSn', visible: true, align: 'center', valign: 'middle'},
        {title: '取水许可证其实日期', field: 'intBd', visible: true, align: 'center', valign: 'middle'},
        {title: '取水许可证结束日期', field: 'intDl', visible: true, align: 'center', valign: 'middle'},
        {title: '取水用户编码', field: 'wiuNm', visible: true, align: 'center', valign: 'middle'},
        {title: '取水口所在行政分区', field: 'adNm', visible: true, align: 'center', valign: 'middle'},
        {title: '水源类型', field: 'sourTpNm', visible: true, align: 'center', valign: 'middle'},
        {title: '取水用途', field: 'intUseNm', visible: true, align: 'center', valign: 'middle'},
        {title: '取水行业', field: 'tradTpNm', visible: true, align: 'center', valign: 'middle'},
        {title: '地下水超采区类型', field: 'gwoaTpNm', visible: true, align: 'center', valign: 'middle'},
        {title: '管网覆盖', field: 'isPipeNm', visible: true, align: 'center', valign: 'middle'},
        {title: '税额等次', field: 'wrtrTpNm', visible: true, align: 'center', valign: 'middle'},
        // {title: '取水口所在水资源分区', field: 'wrzCd', visible: true, align: 'center', valign: 'middle'},
        // {title: '所在地', field: 'addr', visible: true, align: 'center', valign: 'middle'},
        // {title: '取水方式', field: 'intTp', visible: true, align: 'center', valign: 'middle'},
        // {title: '地下水类型（深层地下水和浅层地下水）', field: 'groTp', visible: true, align: 'center', valign: 'middle'},
        // {title: '经度', field: 'lgtd', visible: true, align: 'center', valign: 'middle'},
        // {title: '纬度', field: 'lttd', visible: true, align: 'center', valign: 'middle'},
        // {title: '时间戳', field: 'ts', visible: true, align: 'center', valign: 'middle'},
        // {title: '水源状态', field: 'stateNm', visible: true, align: 'center', valign: 'middle'},
        // {title: '备注', field: 'nt', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
WrPdoInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        WrPdoInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加取水口信息表
 */
WrPdoInfo.openAddWrPdoInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加取水口信息表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/wrPdoInfo/wrPdoInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看取水口信息表详情
 */
WrPdoInfo.openWrPdoInfoDetail = function () {

    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '取水口信息表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wrPdoInfo/wrPdoInfo_update/' + WrPdoInfo.seItem.uuid
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看取水口信息表详情
 */
WrPdoInfo.detail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '取水口信息表详情',
            area: ['800px', '520px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wrPdoInfo/wrPdoInfo_detail/' + WrPdoInfo.seItem.uuid
        });
        this.layerIndex = index;
    }
};

/**
 * 删除取水口信息表
 */
WrPdoInfo.delete = function () {
    if (this.check()) {

        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wrPdoInfo/delete", function (data) {
                Feng.success("删除成功!");
                WrPdoInfo.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("wrPdoInfoId", WrPdoInfo.seItem.uuid);
            ajax.start();
        }

        Feng.confirm("是否刪除该取用水户?", operation);
    }
};

/**
 * 查询取水口信息表列表
 */
WrPdoInfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    WrPdoInfo.table.refresh({query: queryData});
};


/**
 * 分组
 */
WrPdoInfo.grouping = function () {
    var index = layer.open({
        type: 2,
        title: '分组',
        area: ['950px', '460px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/wrPdoGroup/wrPdoGroup_grouping'
    });
    this.layerIndex = index;
}

$(function () {
    var defaultColunms = WrPdoInfo.initColumn();
    var table = new BSTable(WrPdoInfo.id, "/wrPdoInfo/list", defaultColunms);
    table.setPaginationType("server");
    WrPdoInfo.table = table.init();
});
