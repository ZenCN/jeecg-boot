<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <!-- 主表单区域 -->
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="12" >
            <a-form-model-item label="商品编码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="code">
              <a-input v-model="model.code" placeholder="请输入商品编码" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12" >
            <a-form-model-item label="商品名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="name">
              <a-input v-model="model.name" placeholder="请输入商品名称" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12" >
            <a-form-model-item label="商品类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="type">
              <j-dict-select-tag type="list" v-model="model.type" dictCode="product_class" placeholder="请选择商品类型" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12" >
            <a-form-model-item label="重量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="weight">
              <a-input v-model="model.weight" placeholder="请输入重量，选择单位">
                <j-dict-select-tag slot="addonAfter" type="list" v-model="model.unit" dictCode="unit" placeholder="请选择单位" style="width:120px;" />
              </a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12" >
            <a-form-model-item label="商品图片" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="imageString">
              <j-image-upload isMultiple  v-model="model.image" ></j-image-upload>
            </a-form-model-item>
          </a-col>
          <a-col :span="12" >
            <a-form-model-item label="商品描述" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="description">
              <a-textarea v-model="model.description" rows="4" placeholder="请输入商品描述" />
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
      <!-- 子表单区域 -->
    <a-tabs v-model="activeKey" @change="handleChangeTabs">
      <a-tab-pane tab="货物价格表" :key="refKeys[0]" :forceRender="true">
        <j-vxe-table
          keep-source
          :ref="refKeys[0]"
          :loading="erpProductPriceTable.loading"
          :columns="erpProductPriceTable.columns"
          :dataSource="erpProductPriceTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :toolbar="true"
          />
      </a-tab-pane>
    </a-tabs>
  </a-spin>
</template>

<script>

  import { getAction } from '@/api/manage'
  import { JVxeTableModelMixin } from '@/mixins/JVxeTableModelMixin.js'
  import { JVXETypes } from '@/components/jeecg/JVxeTable'
  import { getRefPromise,VALIDATE_FAILED} from '@/components/jeecg/JVxeTable/utils/vxeUtils.js'
  import { validateDuplicateValue, getCurrentDate, getNextSerialNo } from '@/utils/util'
  import JFormContainer from '@/components/jeecg/JFormContainer'

  export default {
    name: 'ErpProductForm',
    mixins: [JVxeTableModelMixin],
    components: {
      JFormContainer,
    },
    data() {
      return {
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        model:{
          code: ''
        },
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
           code: [
              { required: false},
              { validator: (rule, value, callback) => validateDuplicateValue('erp_product', 'code', value, this.model.id, callback)},
           ],
           type: [
              { required: true, message: '请输入商品类型!'},
           ],
        },
        refKeys: ['erpProductPrice', ],
        tableKeys:['erpProductPrice', ],
        activeKey: 'erpProductPrice',
        // 货物价格表
        erpProductPriceTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '进货价',
              key: 'buyPrice',
               type: JVXETypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '批发价',
              key: 'tradePrice',
               type: JVXETypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '样品价',
              key: 'samplePrice',
               type: JVXETypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '币种',
              key: 'currency',
              type: JVXETypes.select,
              options:[],
              dictCode:"currency",
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
          ]
        },
        url: {
          add: "/goods_arch/erpProduct/add",
          edit: "/goods_arch/erpProduct/edit",
          queryById: "/goods_arch/erpProduct/queryById",
          erpProductPrice: {
            list: '/goods_arch/erpProduct/queryErpProductPriceByMainId'
          },
          getSerialNo: "/goods_arch/erpProduct/getSerialNo"
        }
      }
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
    },
    beforeUpdate() {
      if(!this.model.id && !this.model.code){
        let that = this;
        getAction(this.url.getSerialNo).then((res)=>{
          if(res.success){
            if(typeof res.result == 'string' && res.result.length > 0){
              that.model.code = getNextSerialNo(res.result);
            }else {
              that.model.code = 'M-' + getCurrentDate('yyMMdd') + '-001';
            }
          }else{
            that.$message.error(res.message);
          }
        });
      }
    },
    methods: {
      addBefore(){
        this.erpProductPriceTable.dataSource=[]
      },
      getAllTable() {
        let values = this.tableKeys.map(key => getRefPromise(this, key))
        return Promise.all(values)
      },
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        this.$nextTick(() => {
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.erpProductPrice.list, params, this.erpProductPriceTable)
        }
      },
      //校验所有一对一子表表单
        validateSubForm(allValues){
            return new Promise((resolve,reject)=>{
              Promise.all([
              ]).then(() => {
                resolve(allValues)
              }).catch(e => {
                if (e.error === VALIDATE_FAILED) {
                  // 如果有未通过表单验证的子表，就自动跳转到它所在的tab
                  this.activeKey = e.index == null ? this.activeKey : this.refKeys[e.index]
                } else {
                  console.error(e)
                }
              })
            })
        },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        return {
          ...main, // 展开
          erpProductPriceList: allValues.tablesValue[0].tableData,
        }
      },
      validateError(msg){
        this.$message.error(msg)
      },

    }
  }
</script>

<style scoped>
</style>