import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
    {
    title: '入库编号',
    align:"center",
    dataIndex: 'code'
   },
   {
    title: '供应商',
    align:"center",
    dataIndex: 'vendor_dictText'
   },
   {
    title: '货物编码',
    align:"center",
    dataIndex: 'matCode'
   },
   {
    title: '入库数量',
    align:"center",
    dataIndex: 'qty'
   },
   {
    title: '入货仓库',
    align:"center",
    dataIndex: 'stock_dictText'
   },
   {
    title: '备注',
    align:"center",
    dataIndex: 'remark'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "入库编号",
      field: "code",
      component: 'Input',
      colProps: {span: 6},
 	},
	{
      label: "供应商",
      field: "vendor",
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"vendor"
      },
      colProps: {span: 6},
 	},
	{
      label: "货物编码",
      field: "matCode",
      component: 'Input',
      colProps: {span: 6},
 	},
	{
      label: "入货仓库",
      field: "stock",
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"warehouse"
      },
      colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '入库编号',
    field: 'code',
    component: 'Input',
  },
  {
    label: '供应商',
    field: 'vendor',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"vendor"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入供应商!'},
          ];
     },
  },
  {
    label: '货物编码',
    field: 'matCode',
    component: 'Input',
  },
  {
    label: '入库数量',
    field: 'qty',
    component: 'InputNumber',
  },
  {
    label: '入货仓库',
    field: 'stock',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"warehouse"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入入货仓库!'},
          ];
     },
  },
  {
    label: '备注',
    field: 'remark',
    component: 'InputTextArea',//TODO 注意string转换问题
  },
];
