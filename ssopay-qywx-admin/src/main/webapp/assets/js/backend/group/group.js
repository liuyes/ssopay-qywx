define(['jquery', 'bootstrap', 'backend', 'table', 'form', 'jstree'], function ($, undefined, Backend, Table, Form, undefined) {
	//读取选中的条目
    $.jstree.core.prototype.get_all_checked = function (full) {
        var obj = this.get_selected(), i, j;
        for (i = 0, j = obj.length; i < j; i++) {
            obj = obj.concat(this.get_node(obj[i]).parents);
        }
        obj = $.grep(obj, function (v, i, a) {
            return v != '#';
        });
        obj = obj.filter(function (itm, i, a) {
            return i == a.indexOf(itm);
        });
        return full ? $.map(obj, $.proxy(function (i) {
            return this.get_node(i);
        }, this)) : obj;
    };
    var Controller = {
        index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: 'json',
                    add_url: 'add',
                    edit_url: 'edit',
                    del_url: '',
                    multi_url: '',
                }
            });

            var table = $("#table");

            // 初始化表格
            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                sortName: 'id',
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: __('主键'), operate: false},
                        {field: 'name', title: __('名称'), operate: 'LIKE {0}', placeholder: '真实姓名，模糊搜索'},
                        {field: 'disable', title: __('是否有效'), operate: false, formatter: Table.api.formatter.disable, searchList: {'1': __('有效'), '-1': __('无效')}, style: 'min-width:100px;'},
                        {field: 'createDate', title: __('创建日期'), formatter: Table.api.formatter.datetime, operate: false},
                        {field: 'updateDate', title: __('更新日期'), formatter: Table.api.formatter.datetime, operate: false},
                        {field: 'operate', title: __('Operate'), events: Table.api.events.operate, formatter: function (value, row, index) {
        	                	return Table.api.formatter.operate(value, row, index, table);
        	            	}
                        }
                    ]
                ],
                //不分页
                pagination: false,
                //默认搜索
                search: false,
                //启用普通表单搜索
                commonSearch: false,
                //可以控制是否默认显示搜索单表,false则隐藏,默认为false
                searchFormVisible: false,
                //分页大小，不分页时此参数无效
                pageSize: 10,
                //为空则不显示标题，不定义默认显示：普通搜索
                titleForm: '',
            });

            // 为表格绑定事件
            Table.api.bindevent(table);
        },
        add: function () {
        	Form.api.bindevent($("form[role=form]"));
            Controller.api.bindevent();
        },
        edit: function () {
        	Form.api.bindevent($("form[role=form]"));
            Controller.api.bindevent();
        },
        api: {
            refreshrules: function () {
                if ($("#treeview").size() > 0) {
                    var r = $("#treeview").jstree("get_all_checked");
                    $("input[name='rules']").val(r.join(','));
                }
                return true;
            },
            bindevent: function () {
                Form.api.custom.refreshrules = Controller.api.refreshrules;
                //渲染权限节点树
                $(function() {
                    $.ajax({
                        url: ctxUrl + "/group/ruleTree",
                        type: 'post',
                        dataType: 'json',
                        data: {groupId: $("input[name='id']").val()},
                        success: function (ret) {
                            if (ret.hasOwnProperty("code")) {
                                var data = ret.hasOwnProperty("data") && ret.data != "" ? ret.data : "";
                                if (ret.code === 1) {
                                    //销毁已有的节点树
                                    $("#treeview").jstree("destroy");
                                    Controller.api.rendertree(data);
                                } else {
                                    Backend.api.toastr.error(ret.data);
                                }
                            }
                        }, error: function (e) {
                            Backend.api.toastr.error(e.message);
                        }
                    });
                });
                //全选和展开
                $(document).on("click", "#checkall", function () {
                    $("#treeview").jstree($(this).prop("checked") ? "check_all" : "uncheck_all");
                });
                $(document).on("click", "#expandall", function () {
                    $("#treeview").jstree($(this).prop("checked") ? "open_all" : "close_all");
                });
                $("select[name='row[pid]']").trigger("change");
            },
            rendertree: function (content) {
                $("#treeview")
                        .on('redraw.jstree', function (e) {
                            $(".layer-footer").attr("domrefresh", Math.random());
                        })
                        .jstree({
                            "themes": {"stripes": true},
                            "checkbox": {
                                "keep_selected_style": false,
                            },
                            "types": {
                                "root": {
                                    "icon": "fa fa-folder-open",
                                },
                                "menu": {
                                    "icon": "fa fa-folder-open",
                                },
                                "file": {
                                    "icon": "fa fa-file-o",
                                }
                            },
                            "plugins": ["checkbox", "types"],
                            "core": {
                                'check_callback': true,
                                "data": content
                            }
                        });
            }
        }
    };
    return Controller;
});