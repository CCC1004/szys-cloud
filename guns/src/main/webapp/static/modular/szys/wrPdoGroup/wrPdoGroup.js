/**
 * 水源分组管理初始化
 */
var WrPdoGroup = {
    id: "WrPdoGroupTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
WrPdoGroup.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '水源分组代码', field: 'uuid', visible: false, align: 'center', valign: 'middle'},
            {title: '序号', field: 'sn', visible: false, align: 'center', valign: 'middle'},
            {title: '水源分组名称', field: 'groupNm', visible: true, align: 'center', valign: 'middle'},
            {title: '取水口许可证编码', field: 'wpcCd', visible: true, align: 'center', valign: 'middle'},
            {title: '取水许可证号', field: 'wpcSn', visible: false, align: 'center', valign: 'middle'},
            {title: '开始日期', field: 'intBd', visible: false, align: 'center', valign: 'middle'},
            {title: '结束日期', field: 'intDl', visible: false, align: 'center', valign: 'middle'},
            // {title: '取水用户编码', field: 'wiuCd', visible: true, align: 'center', valign: 'middle'},
            {title: '取水用户名称', field: 'wiuNm', visible: true, align: 'center', valign: 'middle'},
            // {title: '水源类型', field: 'sourTp', visible: true, align: 'center', valign: 'middle', formatter:sourTpFormatter},
            {title: '水源类型', field: 'sourTp', visible: true, align: 'center', valign: 'middle'},
            {title: '取水用途', field: 'intUse', visible: true, align: 'center', valign: 'middle'},
            {title: '取水行业', field: 'tradTp', visible: true, align: 'center', valign: 'middle'},
            {title: '地下水超采区类型', field: 'gwoaTp', visible: true, align: 'center', valign: 'middle'},
            {title: '管网覆盖', field: 'isPipe', visible: true, align: 'center', valign: 'middle'},
            {title: '税额等次', field: 'wrtrTp', visible: true, align: 'center', valign: 'middle'},
            {title: '行政分区编码', field: 'adCd', visible: false, align: 'center', valign: 'middle'},
            {title: '行政分区名称', field: 'adNm', visible: true, align: 'center', valign: 'middle'},
            {title: '取水口所在水资源分区', field: 'wrzCd', visible: false, align: 'center', valign: 'middle'},
            {title: '所在地', field: 'addr', visible: false, align: 'center', valign: 'middle'},
            {title: '取水方式', field: 'intTp', visible: false, align: 'center', valign: 'middle'},
            {title: '地下水类型', field: 'groTp', visible: false, align: 'center', valign: 'middle'},
            // {title: '经度', field: 'lgtd', visible: true, align: 'center', valign: 'middle'},
            // {title: '纬度', field: 'lttd', visible: true, align: 'center', valign: 'middle'},
            // {title: '水源状态', field: 'state', visible: true, align: 'center', valign: 'middle'},
            // {title: '备注', field: 'nt', visible: true, align: 'center', valign: 'middle'}
    ];
};

//水源类型（不用该方法赋值）
function sourTpFormatter(value,row,index){
    if(value==1){
        return '地表水';
    }else if(value==2){
        return '地下水';
    }else{
        return '-';
    }
}

/**
 * 检查是否选中
 */
WrPdoGroup.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        WrPdoGroup.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加水源分组
 */
WrPdoGroup.openAddWrPdoGroup = function () {
    var index = layer.open({
        type: 2,
        title: '添加水源分组',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/wrPdoGroup/wrPdoGroup_add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改水源分组
 */
WrPdoGroup.openWrPdoGroupUpdate = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改水源分组',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wrPdoGroup/wrPdoGroup_update/' + WrPdoGroup.seItem.uuid
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看水源分组详情
 */
WrPdoGroup.openWrPdoGroupDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '水源分组详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wrPdoGroup/wrPdoGroup_detail/' + WrPdoGroup.seItem.uuid
        });
        this.layerIndex = index;
    }
};

/**
 * 删除水源分组
 */
WrPdoGroup.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/wrPdoGroup/delete", function (data) {
            Feng.success("删除成功!");
            WrPdoGroup.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("wrPdoGroupId",this.seItem.uuid);
        ajax.start();
    }
};


/**
 * 模糊查询参数
 * @returns {{}}
 */
WrPdoGroup.formParams = function() {
    var queryData = {};

    queryData['groupNm'] = $("#groupNm").val();
    queryData['wpcSn'] = $("#wpcSn").val();
    queryData['sourTp'] = $("#sourTp").val();
    queryData['intUse'] = $("#intUse").val();
    queryData['tradTp'] = $("#tradTp").val();
    queryData['gwoaTp'] = $("#gwoaTp").val();
    queryData['isPipe'] = $("#isPipe").val();
    queryData['wrtrTp'] = $("#wrtrTp").val();

    return queryData;
}

/**
 * 查询水源分组列表
 */
WrPdoGroup.search = function () {
    var queryData = {};
    queryData = WrPdoGroup.formParams();
    WrPdoGroup.table.refresh({query: queryData});
};

/**
 * 分组(弃用，移至取水口信息列表)
 */
WrPdoGroup.grouping = function () {
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
    var defaultColunms = WrPdoGroup.initColumn();
    var table = new BSTable(WrPdoGroup.id, "/wrPdoGroup/list", defaultColunms);
    //分页
    table.setPaginationType("server");
    //查询条件参数
    table.setQueryParams(WrPdoGroup.formParams());
    WrPdoGroup.table = table.init();
});


