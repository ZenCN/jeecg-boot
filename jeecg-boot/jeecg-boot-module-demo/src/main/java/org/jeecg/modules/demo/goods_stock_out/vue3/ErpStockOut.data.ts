import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
    {
    title: '出库编码',
    align:"center",
    dataIndex: 'code'
   },
   {
    title: '客户',
    align:"center",
    dataIndex: 'customer_dictText'
   },
   {
    title: '货物编码',
    align:"center",
    dataIndex: 'matCode'
   },
   {
    title: '出库数量',
    align:"center",
    dataIndex: 'qty'
   },
   {
    title: '出货仓库',
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
      label: "出库编码",
      field: "code",
      component: 'Input',
      colProps: {span: 6},
 	},
	{
      label: "客户",
      field: "customer",
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"customer"
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
      label: "出货仓库",
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
    label: '出库编码',
    field: 'code',
    component: 'Input',
  },
  {
    label: '客户',
    field: 'customer',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"customer"
     },
  },
  {
    label: '货物编码',
    field: 'matCode',
    component: 'Input',
  },
  {
    label: '出库数量',
    field: 'qty',
    component: 'InputNumber',
  },
  {
    label: '出货仓库',
    field: 'stock',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"warehouse"
     },
  },
  {
    label: '备注',
    field: 'remark',
    component: 'InputTextArea',//TODO 注意string转换问题
  },
];
