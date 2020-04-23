<template>
  <a-modal
    :title="title"
    :width="800"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
      
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="公司编号">
          <a-input placeholder="请输入公司编号" v-decorator="['factNo', validatorRules.factNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="SKU">
          <a-input placeholder="请输入SKU" v-decorator="['sku', validatorRules.sku ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="SPU">
          <a-input placeholder="请输入SPU" v-decorator="['spu', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="EAN">
          <a-input placeholder="请输入EAN" v-decorator="['ean', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="质检标准">
          <a-input placeholder="请输入质检标准" v-decorator="['qualityInspectStd', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="所属分类类目">
          <a-input placeholder="请输入所属分类类目" v-decorator="['classification', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="开发序列号">
          <a-input placeholder="请输入开发序列号" v-decorator="['developSn', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="开发链接">
          <a-input placeholder="请输入开发链接" v-decorator="['developLink', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="状态">
          <a-input placeholder="请输入状态" v-decorator="['status', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="SKU采购名称">
          <a-input placeholder="请输入SKU采购名称" v-decorator="['skuPurchaseNm', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="英文名称">
          <a-input placeholder="请输入英文名称" v-decorator="['engNm', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="速卖通英文名称">
          <a-input placeholder="请输入速卖通英文名称" v-decorator="['engNmSmt', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="速卖通最佳LISTING">
          <a-input placeholder="请输入速卖通最佳LISTING" v-decorator="['bestListingSmt', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="EBAY最佳LISTING">
          <a-input placeholder="请输入EBAY最佳LISTING" v-decorator="['bestListingEbay', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="长(自带包装)">
          <a-input placeholder="请输入长(自带包装)" v-decorator="['netLength', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="宽(自带包装)">
          <a-input placeholder="请输入宽(自带包装)" v-decorator="['netWidth', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="高(自带包装)">
          <a-input placeholder="请输入高(自带包装)" v-decorator="['netHeight', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="长(打包后)">
          <a-input placeholder="请输入长(打包后)" v-decorator="['grossLength', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="宽(打包后)">
          <a-input placeholder="请输入宽(打包后)" v-decorator="['grossWidth', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="高(打包后)">
          <a-input placeholder="请输入高(打包后)" v-decorator="['grossHeight', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="SKU物流属性">
          <a-input placeholder="请输入SKU物流属性" v-decorator="['wlAttribute', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="海关编码">
          <a-input placeholder="请输入海关编码" v-decorator="['customsCode', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="税率">
          <a-input placeholder="请输入税率" v-decorator="['taxRate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="参考价">
          <a-input placeholder="请输入参考价" v-decorator="['refPrice', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="重量(自带包装)">
          <a-input placeholder="请输入重量(自带包装)" v-decorator="['netWeight', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="重量(打包后)">
          <a-input placeholder="请输入重量(打包后)" v-decorator="['grossWeight', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="储位">
          <a-input placeholder="请输入储位" v-decorator="['storageArea', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="采购开发">
          <a-input placeholder="请输入采购开发" v-decorator="['purchaseDevelopEmp', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="订货员">
          <a-input placeholder="请输入订货员" v-decorator="['orderGoodsEmp', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="订货主管">
          <a-input placeholder="请输入订货主管" v-decorator="['orderGoodsDirector', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="计划员">
          <a-input placeholder="请输入计划员" v-decorator="['planner', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="备注">
          <a-input placeholder="请输入备注" v-decorator="['remarks', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="关键词">
          <a-input placeholder="请输入关键词" v-decorator="['keywords', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="容量(液体或电池类产品)">
          <a-input placeholder="请输入容量(液体或电池类产品)" v-decorator="['capacitys', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="产品属性">
          <a-input placeholder="请输入产品属性" v-decorator="['prodAttribute', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="产品多属性信息">
          <a-input placeholder="请输入产品多属性信息" v-decorator="['prodAttributeInfo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="产品信息">
          <a-input placeholder="请输入产品信息" v-decorator="['prodInfo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="合规图">
          <a-input placeholder="请输入合规图" v-decorator="['compliancePicture', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="合规审核结果">
          <a-input placeholder="请输入合规审核结果" v-decorator="['complianceAuditResult', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="风险等级">
          <a-input placeholder="请输入风险等级" v-decorator="['riskLevel', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="不可上架平台站点">
          <a-input placeholder="请输入不可上架平台站点" v-decorator="['noUploadSite', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="制式">
          <a-input placeholder="请输入制式" v-decorator="['form', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="型号">
          <a-input placeholder="请输入型号" v-decorator="['modelNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="产品说明书">
          <a-input placeholder="请输入产品说明书" v-decorator="['prodInstructions', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="执行标准">
          <a-input placeholder="请输入执行标准" v-decorator="['execStandard', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="摄影师">
          <a-input placeholder="请输入摄影师" v-decorator="['photographer', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="图片美工">
          <a-input placeholder="请输入图片美工" v-decorator="['photoProcesser', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="英文编辑">
          <a-input placeholder="请输入英文编辑" v-decorator="['engEditor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="效果跟进员">
          <a-input placeholder="请输入效果跟进员" v-decorator="['effectFollower', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="序号">
          <a-input placeholder="请输入序号" v-decorator="['sN', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="批号">
          <a-input placeholder="请输入批号" v-decorator="['lotNo', {}]" />
        </a-form-item>
		
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: "PurSupBasicDataModal",
    data () {
      return {
        title:"操作",
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },

        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules:{
        factNo:{rules: [{ required: true, message: '请输入公司编号!' }]},
        sku:{rules: [{ required: true, message: '请输入SKU!' }]},
        },
        url: {
          add: "/scan/purSupBasicData/add",
          edit: "/scan/purSupBasicData/edit",
        },
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'factNo','sku','spu','ean','qualityInspectStd','classification','developSn','developLink','status','skuPurchaseNm','engNm','engNmSmt','bestListingSmt','bestListingEbay','netLength','netWidth','netHeight','grossLength','grossWidth','grossHeight','wlAttribute','customsCode','taxRate','refPrice','netWeight','grossWeight','storageArea','purchaseDevelopEmp','orderGoodsEmp','orderGoodsDirector','planner','remarks','keywords','capacitys','prodAttribute','prodAttributeInfo','prodInfo','compliancePicture','complianceAuditResult','riskLevel','noUploadSite','form','modelNo','prodInstructions','execStandard','photographer','photoProcesser','engEditor','effectFollower','sN','lotNo'))
		  //时间格式化
        });

      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
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
            let formData = Object.assign(this.model, values);
            //时间格式化
            
            console.log(formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })



          }
        })
      },
      handleCancel () {
        this.close()
      },


    }
  }
</script>

<style lang="less" scoped>

</style>