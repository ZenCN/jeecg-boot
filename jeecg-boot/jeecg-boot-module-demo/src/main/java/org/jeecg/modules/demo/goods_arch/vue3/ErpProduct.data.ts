import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import {JVxeTypes,JVxeColumn} from '/@/components/jeecg/JVxeTable/types'
//列表数据
export const columns: BasicColumn[] = [
    {
    title: '商品编码',
    align:"center",
    dataIndex: 'code'
   },
   {
    title: '商品名称',
    align:"center",
    dataIndex: 'name'
   },
   {
    title: '商品类型',
    align:"center",
    dataIndex: 'type_dictText'
   },
   {
    title: '重量',
    align:"center",
    dataIndex: 'weight'
   },
   {
    title: '单位',
    align:"center",
    dataIndex: 'unit_dictText'
   },
   {
    title: '商品图片',
    align:"center",
    dataIndex: 'imageString'
   },
   {
    title: '商品描述',
    align:"center",
    dataIndex: 'description'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "商品编码",
      field: "code",
      component: 'Input',
      colProps: {span: 6},
 	},
	{
      label: "商品名称",
      field: "name",
      component: 'Input',
      colProps: {span: 6},
 	},
	{
      label: "商品类型",
      field: "type",
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"product_class"
      },
      colProps: {span: 6},
 	},
	{
      label: "商品描述",
      field: "description",
      component: 'Input',
      colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '商品编码',
    field: 'code',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: false},
                 {...rules.duplicateCheckRule('erp_product', 'code',model,schema,true)[0]},
          ];
     },
  },
  {
    label: '商品名称',
    field: 'name',
    component: 'Input',
  },
  {
    label: '商品类型',
    field: 'type',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"product_class"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入商品类型!'},
          ];
     },
  },
  {
    label: '重量',
    field: 'weight',
    component: 'InputNumber',
  },
  {
    label: '单位',
    field: 'unit',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"unit"
     },
  },
  {
    label: '商品图片',
    field: 'image',
     component: 'JImageUpload',
     componentProps:{
      },
  },
  {
    label: '商品描述',
    field: 'description',
    component: 'InputTextArea',//TODO 注意string转换问题
  },
];
//子表单数据
//子表表格配置
export const erpProductPriceColumns: JVxeColumn[] = [
    {
      title: '进货价',
      key: 'buyPrice',
       type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
      validateRules: [{ required: true, message: '${title}不能为空' }],
    },
    {
      title: '批发价',
      key: 'tradePrice',
       type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
      validateRules: [{ required: true, message: '${title}不能为空' }],
    },
    {
      title: '样品价',
      key: 'samplePrice',
       type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '币种',
      key: 'currency',
      type: JVxeTypes.select,
      options:[],
      dictCode:"currency",
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
      validateRules: [{ required: true, message: '${title}不能为空' }],
    },
  ]
