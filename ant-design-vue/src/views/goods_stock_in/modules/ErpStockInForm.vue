<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="12">
            <a-form-model-item label="入库编号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="code">
              <a-input v-model="model.code" placeholder="请输入入库编号" :disabled="true"></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="供应商" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="vendor">
              <j-dict-select-tag type="list" v-model="model.vendor" dictCode="vendor" placeholder="请选择供应商" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="货物编码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="matCode">
              <a-auto-complete class="certain-category-search" v-model="model.matCode" :data-source="dataSource"
                placeholder="请输入货物编码或名称" @search="onSearch" option-label-prop="value">
                <template slot="dataSource">
                  <a-select-option v-for="opt in dataSource" :key="opt.key" :value="opt.key">
                    {{ opt.key + ' ' + opt.val }}
                    <span class="certain-search-item-count" v-show="opt.key!='empty'">100个</span>
                  </a-select-option>
                </template>
                <a-input>
                  <a-icon slot="suffix" type="search" class="certain-category-icon" />
                </a-input>
              </a-auto-complete>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="入库数量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="qty">
              <a-input-number v-model="model.qty" placeholder="请输入入库数量" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="入货仓库" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="stock">
              <j-dict-select-tag type="list" v-model="model.stock" dictCode="warehouse" placeholder="请选择入货仓库" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="remark">
              <a-textarea v-model="model.remark" rows="4" placeholder="请输入备注" />
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { getCurrentDate, getNextSerialNo } from '@/utils/util'

  export default {
    name: 'ErpStockInForm',
    components: {
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        model:{
          code: ''
        },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
           vendor: [
              { required: true, message: '请输入供应商!'},
           ],
           stock: [
              { required: true, message: '请输入入货仓库!'},
           ],
        },
        url: {
          add: "/goods_stock_in/erpStockIn/add",
          edit: "/goods_stock_in/erpStockIn/edit",
          queryById: "/goods_stock_in/erpStockIn/queryById",
          getSerialNo: "/goods_stock_in/erpStockIn/getSerialNo",
          queryMatCodeName: "/goods_arch/erpProduct/queryMatCodeName"
        },
        dataSource: []
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
       //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
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
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      submitForm () {
        const that = this;
        //触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }
         
        })
      },
      onSearch(keyWords) {
        let that = this;
        window.that = that;
        if(keyWords)
          getAction(this.url.queryMatCodeName, { keyWords: keyWords }).then((res) => {
            if(res.success){
              if(typeof res.result == 'object' && res.result.length > 0){
                that.dataSource = [];
                for(let str of res.result)
                  that.dataSource.push({key:str.substr(0, str.indexOf('|')),val:str.substr(str.indexOf('|')+1)});
              }else {
                that.dataSource = [{key:'empty',val:'未匹配到'}];
              }
            }else{
              that.$message.error(res.message);
            }
          });
      }
    }
  }
</script>