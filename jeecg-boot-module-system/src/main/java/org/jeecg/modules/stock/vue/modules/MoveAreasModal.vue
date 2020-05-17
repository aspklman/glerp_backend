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
          label="厂区编号">
          <a-input placeholder="请输入厂区编号" v-decorator="['factNo', validatorRules.factNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="库别编号">
          <a-input placeholder="请输入库别编号" v-decorator="['stkNo', validatorRules.stkNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="库存年月">
          <a-input placeholder="请输入库存年月" v-decorator="['stockYymm', validatorRules.stockYymm ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="订单批号">
          <a-input placeholder="请输入订单批号" v-decorator="['factOdrNo', validatorRules.factOdrNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="存放区编号">
          <a-input placeholder="请输入存放区编号" v-decorator="['locNo', validatorRules.locNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="成本部位">
          <a-input placeholder="请输入成本部位" v-decorator="['cpartNo', validatorRules.cpartNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="仓管员编号">
          <a-input placeholder="请输入仓管员编号" v-decorator="['whmanNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="存放区域流水号">
          <a-input placeholder="请输入存放区域流水号" v-decorator="['locateSeq', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="现在库存量">
          <a-input placeholder="请输入现在库存量" v-decorator="['stockQty', validatorRules.stockQty ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="期初库存量">
          <a-input placeholder="请输入期初库存量" v-decorator="['startQty', validatorRules.startQty ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="本月入库量">
          <a-input placeholder="请输入本月入库量" v-decorator="['inQty', validatorRules.inQty ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="本月出库量">
          <a-input placeholder="请输入本月出库量" v-decorator="['outQty', validatorRules.outQty ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="调整入库量">
          <a-input placeholder="请输入调整入库量" v-decorator="['fallotInQty', validatorRules.fallotInQty ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="调整出库量">
          <a-input placeholder="请输入调整出库量" v-decorator="['fallotOutQty', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="盘盈亏数量">
          <a-input placeholder="请输入盘盈亏数量" v-decorator="['waxQty', validatorRules.waxQty ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="存放地点">
          <a-input placeholder="请输入存放地点" v-decorator="['locArea', validatorRules.locArea ]" />
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
    name: "MoveAreasModal",
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
        factNo:{rules: [{ required: true, message: '请输入厂区编号!' }]},
        stkNo:{rules: [{ required: true, message: '请输入库别编号!' }]},
        stockYymm:{rules: [{ required: true, message: '请输入库存年月!' }]},
        factOdrNo:{rules: [{ required: true, message: '请输入订单批号!' }]},
        locNo:{rules: [{ required: true, message: '请输入存放区编号!' }]},
        cpartNo:{rules: [{ required: true, message: '请输入成本部位!' }]},
        stockQty:{rules: [{ required: true, message: '请输入现在库存量!' }]},
        startQty:{rules: [{ required: true, message: '请输入期初库存量!' }]},
        inQty:{rules: [{ required: true, message: '请输入本月入库量!' }]},
        outQty:{rules: [{ required: true, message: '请输入本月出库量!' }]},
        fallotInQty:{rules: [{ required: true, message: '请输入调整入库量!' }]},
        waxQty:{rules: [{ required: true, message: '请输入盘盈亏数量!' }]},
        locArea:{rules: [{ required: true, message: '请输入存放地点!' }]},
        },
        url: {
          add: "/stock/moveAreas/add",
          edit: "/stock/moveAreas/edit",
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
          this.form.setFieldsValue(pick(this.model,'factNo','stkNo','stockYymm','factOdrNo','locNo','cpartNo','whmanNo','locateSeq','stockQty','startQty','inQty','outQty','fallotInQty','fallotOutQty','waxQty','locArea'))
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