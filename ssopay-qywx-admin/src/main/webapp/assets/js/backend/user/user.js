define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: 'json',
                    add_url: 'add',
                    edit_url: 'edit',
                    del_url: '',
                    multi_url: 'multi',
                    table: 'user',
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
                        {field: 'username', title: __('用户名'), operate: 'LIKE {0}', placeholder: '用户名，模糊搜索'},
                        {field: 'name', title: __('真实姓名'), operate: 'LIKE {0}', placeholder: '真实姓名，模糊搜索'},
                        {field: 'email', title: __('邮箱'), operate: false},
                        {field: 'mobile', title: __('联系电话'), operate: false},
                        {field: 'disable', title: __('是否有效'), formatter: Table.api.formatter.disable, searchList: {'1': __('有效'), '-1': __('无效')}, style: 'min-width:100px;'},
                        {field: 'createDate', title: __('创建日期'), formatter: Table.api.formatter.datetime, operate: 'BETWEEN', type: 'datetime', addclass: 'datetimepicker', data: 'data-date-format="YYYY-MM-DD"'},
                        {field: 'updateDate', title: __('更新日期'), formatter: Table.api.formatter.datetime, operate: false},
                        {field: 'operate', title: __('Operate'), events: Table.api.events.operate, formatter: function (value, row, index) {
    	                		return Table.api.formatter.operate(value, row, index, table);
	    	            	}
	                    }
                    ]
                ],
                //默认搜索
                search: false,
                //启用普通表单搜索
                commonSearch: true,
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
            Controller.api.bindevent();
        },
        edit: function () {
            Controller.api.bindevent();
        },
        api: {
            bindevent: function () {
                Form.api.bindevent($("form[role=form]"));
            }
        }
    };
    return Controller;
});