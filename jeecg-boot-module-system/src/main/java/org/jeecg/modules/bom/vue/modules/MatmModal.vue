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
          label="材料编号">
          <a-input placeholder="请输入材料编号" v-decorator="['matNo', validatorRules.matNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="材料名称">
          <a-input placeholder="请输入材料名称" v-decorator="['matNm', validatorRules.matNm ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="材料英文名称">
          <a-input placeholder="请输入材料英文名称" v-decorator="['matEngNm', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="材料单位">
          <a-input placeholder="请输入材料单位" v-decorator="['matUnit', validatorRules.matUnit ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="材料英文单位">
          <a-input placeholder="请输入材料英文单位" v-decorator="['matEngUnit', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="材料颜色">
          <a-input placeholder="请输入材料颜色" v-decorator="['matColor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="英文颜色">
          <a-input placeholder="请输入英文颜色" v-decorator="['matEngColor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="采购政策">
          <a-input placeholder="请输入采购政策" v-decorator="['purPolicy', validatorRules.purPolicy ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="平均单价锁定">
          <a-input placeholder="请输入平均单价锁定" v-decorator="['accNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="资材代号">
          <a-input placeholder="请输入资材代号" v-decorator="['accMatno', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="安全库存">
          <a-input placeholder="请输入安全库存" v-decorator="['safeStkQty', validatorRules.safeStkQty ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="采购提前期">
          <a-input placeholder="请输入采购提前期" v-decorator="['leadTime', validatorRules.leadTime ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="托外注记">
          <a-input placeholder="请输入托外注记" v-decorator="['preprdRmk', validatorRules.preprdRmk ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="尺寸注记">
          <a-input placeholder="请输入尺寸注记" v-decorator="['sizeRmk', validatorRules.sizeRmk ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="超交允收">
          <a-input placeholder="请输入超交允收" v-decorator="['overRcvRmk', validatorRules.overRcvRmk ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="商品编号">
          <a-input placeholder="请输入商品编号" v-decorator="['goodsNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="序号">
          <a-input placeholder="请输入序号" v-decorator="['goodsSeq', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="单重">
          <a-input placeholder="请输入单重" v-decorator="['unitWeight', validatorRules.unitWeight ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="已订未交量">
          <a-input placeholder="请输入已订未交量" v-decorator="['onOrderQty', validatorRules.onOrderQty ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="已收未验量">
          <a-input placeholder="请输入已收未验量" v-decorator="['unqcQty', validatorRules.unqcQty ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="最近进料日">
          <a-input placeholder="请输入最近进料日" v-decorator="['lastInDate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="最近出库日">
          <a-input placeholder="请输入最近出库日" v-decorator="['lastOutDate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="异动人">
          <a-input placeholder="请输入异动人" v-decorator="['userNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="异动时间">
          <a-input placeholder="请输入异动时间" v-decorator="['modifyDt', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="锁定注记">
          <a-input placeholder="请输入锁定注记" v-decorator="['lockStatus', validatorRules.lockStatus ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="锁定日期">
          <a-input placeholder="请输入锁定日期" v-decorator="['lockDate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="换算单位">
          <a-input placeholder="请输入换算单位" v-decorator="['unitRate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="共用注记">
          <a-input placeholder="请输入共用注记" v-decorator="['shareRmk', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="物性测试注记">
          <a-input placeholder="请输入物性测试注记" v-decorator="['matphyRmk', validatorRules.matphyRmk ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="检验注记">
          <a-input placeholder="请输入检验注记" v-decorator="['sgsRmk', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="材料类别编号">
          <a-input placeholder="请输入材料类别编号" v-decorator="['matkindNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="材料简称">
          <a-input placeholder="请输入材料简称" v-decorator="['uMat', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="预购注记">
          <a-input placeholder="请输入预购注记" v-decorator="['apRmk', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="单重审核注记(Y/N)">
          <a-input placeholder="请输入单重审核注记(Y/N)" v-decorator="['wgtVerifyRmk', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="单重审核人">
          <a-input placeholder="请输入单重审核人" v-decorator="['wgtVerifyUser', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="单重审核日">
          <a-input placeholder="请输入单重审核日" v-decorator="['wgtVerifyDate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="关务材料名">
          <a-input placeholder="请输入关务材料名" v-decorator="['matNmGw', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="创建日期">
          <a-input placeholder="请输入创建日期" v-decorator="['creatDate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="前次收货日">
          <a-input placeholder="请输入前次收货日" v-decorator="['lRecDate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="送测进料批次">
          <a-input placeholder="请输入送测进料批次" v-decorator="['incomQty', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="盘点单价">
          <a-input placeholder="请输入盘点单价" v-decorator="['avgUnitprice', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="保税注记1.保税 2.非保税">
          <a-input placeholder="请输入保税注记1.保税 2.非保税" v-decorator="['taxRmk', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="IN_ACC_NO">
          <a-input placeholder="请输入IN_ACC_NO" v-decorator="['inAccNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="材料材质属性">
          <a-input placeholder="请输入材料材质属性" v-decorator="['matSpec', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="loss比率">
          <a-input placeholder="请输入loss比率" v-decorator="['lossRate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="币别">
          <a-input placeholder="请输入币别" v-decorator="['coinKind', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="最小用量">
          <a-input placeholder="请输入最小用量" v-decorator="['minLot', validatorRules.minLot ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="审核人">
          <a-input placeholder="请输入审核人" v-decorator="['verifyUser', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="审核时间">
          <a-input placeholder="请输入审核时间" v-decorator="['verifyDt', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="去掉空格的名称">
          <a-input placeholder="请输入去掉空格的名称" v-decorator="['nmTrim', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="是否测试">
          <a-input placeholder="请输入是否测试" v-decorator="['istest', {}]" />
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
    name: "MatmModal",
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
        matNo:{rules: [{ required: true, message: '请输入材料编号!' }]},
        matNm:{rules: [{ required: true, message: '请输入材料名称!' }]},
        matUnit:{rules: [{ required: true, message: '请输入材料单位!' }]},
        purPolicy:{rules: [{ required: true, message: '请输入采购政策!' }]},
        safeStkQty:{rules: [{ required: true, message: '请输入安全库存!' }]},
        leadTime:{rules: [{ required: true, message: '请输入采购提前期!' }]},
        preprdRmk:{rules: [{ required: true, message: '请输入托外注记!' }]},
        sizeRmk:{rules: [{ required: true, message: '请输入尺寸注记!' }]},
        overRcvRmk:{rules: [{ required: true, message: '请输入超交允收!' }]},
        unitWeight:{rules: [{ required: true, message: '请输入单重!' }]},
        onOrderQty:{rules: [{ required: true, message: '请输入已订未交量!' }]},
        unqcQty:{rules: [{ required: true, message: '请输入已收未验量!' }]},
        lockStatus:{rules: [{ required: true, message: '请输入锁定注记!' }]},
        matphyRmk:{rules: [{ required: true, message: '请输入物性测试注记!' }]},
        minLot:{rules: [{ required: true, message: '请输入最小用量!' }]},
        },
        url: {
          add: "/bom/matm/add",
          edit: "/bom/matm/edit",
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
          this.form.setFieldsValue(pick(this.model,'factNo','matNo','matNm','matEngNm','matUnit','matEngUnit','matColor','matEngColor','purPolicy','accNo','accMatno','safeStkQty','leadTime','preprdRmk','sizeRmk','overRcvRmk','goodsNo','goodsSeq','unitWeight','onOrderQty','unqcQty','lastInDate','lastOutDate','userNo','modifyDt','lockStatus','lockDate','unitRate','shareRmk','matphyRmk','sgsRmk','matkindNo','uMat','apRmk','wgtVerifyRmk','wgtVerifyUser','wgtVerifyDate','matNmGw','creatDate','lRecDate','incomQty','avgUnitprice','taxRmk','inAccNo','matSpec','lossRate','coinKind','minLot','verifyUser','verifyDt','nmTrim','istest'))
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