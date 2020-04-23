<template>
  <a-modal
    :title="title"
    :width="1200"
    :visible="visible"
    :maskClosable="false"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel">
    <a-spin :spinning="confirmLoading">
      <!-- 主表单区域 -->
      <a-form :form="form">
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="需出货量">
              <a-input placeholder="请输入需出货量" v-decorator="['cusQty', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="税率">
              <a-input placeholder="请输入税率" v-decorator="['taxRate', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="未税价">
              <a-input placeholder="请输入未税价" v-decorator="['taxPrice', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="合同交期">
              <a-input placeholder="请输入合同交期" v-decorator="['chd', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="实际交期">
              <a-input placeholder="请输入实际交期" v-decorator="['ahd', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="厂区编号">
              <a-input placeholder="请输入厂区编号" v-decorator="['factNo', validatorRules.factNo ]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="订单批号">
              <a-input placeholder="请输入订单批号" v-decorator="['factOdrNo', validatorRules.factOdrNo ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="客户编号">
              <a-input placeholder="请输入客户编号" v-decorator="['customNo', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="唛头编号">
              <a-input placeholder="请输入唛头编号" v-decorator="['markNo', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="线别编号">
              <a-input placeholder="请输入线别编号" v-decorator="['lineNo', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="型体编号">
              <a-input placeholder="请输入型体编号" v-decorator="['styleNo', validatorRules.styleNo ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="品牌编号">
              <a-input placeholder="请输入品牌编号" v-decorator="['brandNo', validatorRules.brandNo ]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="楦头肥度">
              <a-input placeholder="请输入楦头肥度" v-decorator="['lastWidth', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="冲销po信息">
              <a-input placeholder="请输入冲销po信息" v-decorator="['poNo', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="生产部别">
              <a-input placeholder="请输入生产部别" v-decorator="['proDept', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="客户订单编号">
              <a-input placeholder="请输入客户订单编号" v-decorator="['custOdrNo', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="客户要求交期">
              <a-input placeholder="请输入客户要求交期" v-decorator="['custReqDate', validatorRules.custReqDate ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="接单日期">
              <a-input placeholder="请输入接单日期" v-decorator="['revOdrDate', validatorRules.revOdrDate ]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="预计用量编号">
              <a-input placeholder="请输入预计用量编号" v-decorator="['purplanNo', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="第一次确认日">
              <a-input placeholder="请输入第一次确认日" v-decorator="['fCfmDate', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="最后确认日">
              <a-input placeholder="请输入最后确认日" v-decorator="['lCfmDate', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="SIZE种类">
              <a-input placeholder="请输入SIZE种类" v-decorator="['sizeKind', validatorRules.sizeKind ]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="订单双数">
              <a-input placeholder="请输入订单双数" v-decorator="['odrQty', validatorRules.odrQty ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="每箱双数">
              <a-input placeholder="请输入每箱双数" v-decorator="['ctnPair', validatorRules.ctnPair ]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="总箱数">
              <a-input placeholder="请输入总箱数" v-decorator="['odrTotCtn', validatorRules.odrTotCtn ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="安排验货日">
              <a-input placeholder="请输入安排验货日" v-decorator="['arrInsDate', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="实际验货日">
              <a-input placeholder="请输入实际验货日" v-decorator="['actInsDate', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="实际出货日">
              <a-input placeholder="请输入实际出货日" v-decorator="['shipDate', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="取消日期">
              <a-input placeholder="请输入取消日期" v-decorator="['cancelDate', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="目的地">
              <a-input placeholder="请输入目的地" v-decorator="['destCountry', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="目的港口">
              <a-input placeholder="请输入目的港口" v-decorator="['destPort', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="订单年月">
              <a-input placeholder="请输入订单年月" v-decorator="['odrYymm', validatorRules.odrYymm ]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="订单类别1.正式 2.销样">
              <a-input placeholder="请输入订单类别1.正式 2.销样" v-decorator="['odrKind', validatorRules.odrKind ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="订单性质1.正式 2.客户PO 3.预购">
              <a-input placeholder="请输入订单性质1.正式 2.客户PO 3.预购" v-decorator="['odrType', validatorRules.odrType ]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="原订单厂别">
              <a-input placeholder="请输入原订单厂别" v-decorator="['oriFctryNo', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="代工厂别">
              <a-input placeholder="请输入代工厂别" v-decorator="['outworkFctryNo', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="用量产生时间">
              <a-input placeholder="请输入用量产生时间" v-decorator="['purplanDate', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="主料加成比">
              <a-input placeholder="请输入主料加成比" v-decorator="['mainmatAddRate', validatorRules.mainmatAddRate ]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="副料加成比">
              <a-input placeholder="请输入副料加成比" v-decorator="['submatAddRate', validatorRules.submatAddRate ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="厂内交期">
              <a-input placeholder="请输入厂内交期" v-decorator="['factReqDate', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="出货数量">
              <a-input placeholder="请输入出货数量" v-decorator="['shipQty', validatorRules.shipQty ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="币别">
              <a-input placeholder="请输入币别" v-decorator="['coinKind', validatorRules.coinKind ]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="含税单价">
              <a-input placeholder="请输入含税单价" v-decorator="['unitPrice', validatorRules.unitPrice ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="异动人">
              <a-input placeholder="请输入异动人" v-decorator="['userNo', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="异动时间">
              <a-input placeholder="请输入异动时间" v-decorator="['modifyDt', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="预计用量版次">
              <a-input placeholder="请输入预计用量版次" v-decorator="['edition', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="用量转档注记">
              <a-input placeholder="请输入用量转档注记" v-decorator="['tranRmk', validatorRules.tranRmk ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="暂定版SIZE编号">
              <a-input placeholder="请输入暂定版SIZE编号" v-decorator="['sizeNo', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="验货数量">
              <a-input placeholder="请输入验货数量" v-decorator="['insTqty', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="SKU NO 开发版本编号">
              <a-input placeholder="请输入SKU NO 开发版本编号" v-decorator="['skuNo', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="固定装注记1斩刀用量 2订单主档">
              <a-input placeholder="请输入固定装注记1斩刀用量 2订单主档" v-decorator="['ctnFixRmk', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="翻箱次数">
              <a-input placeholder="请输入翻箱次数" v-decorator="['siCheckNum', validatorRules.siCheckNum ]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="生产年月">
              <a-input placeholder="请输入生产年月" v-decorator="['productYymm', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="生产周次">
              <a-input placeholder="请输入生产周次" v-decorator="['productWeek', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="销售注记">
              <a-input placeholder="请输入销售注记" v-decorator="['salRmk', validatorRules.salRmk ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="生产指令确认注记">
              <a-input placeholder="请输入生产指令确认注记" v-decorator="['proeRmk', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="生产指令确认人">
              <a-input placeholder="请输入生产指令确认人" v-decorator="['proeUser', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="生产指令确认时间">
              <a-input placeholder="请输入生产指令确认时间" v-decorator="['proeDt', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="实际生产栋别">
              <a-input placeholder="请输入实际生产栋别" v-decorator="['aproDept', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="增值代码">
              <a-input placeholder="请输入增值代码" v-decorator="['vaCode', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="条码批号">
              <a-input placeholder="请输入条码批号" v-decorator="['lotNo', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="备注">
              <a-input placeholder="请输入备注" v-decorator="['remark', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="销售类别(财务)">
              <a-input placeholder="请输入销售类别(财务)" v-decorator="['salKind', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="请款数量">
              <a-input placeholder="请输入请款数量" v-decorator="['payQty', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="审核注记">
              <a-input placeholder="请输入审核注记" v-decorator="['verifyRmk', validatorRules.verifyRmk ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="审核人">
              <a-input placeholder="请输入审核人" v-decorator="['verifyUser', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="审核时间">
              <a-input placeholder="请输入审核时间" v-decorator="['verifyDt', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="需成品鞋利库">
              <a-input placeholder="请输入需成品鞋利库" v-decorator="['avaRmk', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="外包bom配色">
              <a-input placeholder="请输入外包bom配色" v-decorator="['matchNo', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>

      <!-- 子表单区域 -->
      <a-tabs v-model="activeKey" @change="handleChangeTabs">
        <a-tab-pane tab="订单子表" :key="refKeys[0]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[0]"
            :loading="odrdTable.loading"
            :columns="odrdTable.columns"
            :dataSource="odrdTable.dataSource"
            :maxHeight="300"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
        </a-tab-pane>
      </a-tabs>

    </a-spin>
  </a-modal>
</template>

<script>

  import moment from 'moment'
  import pick from 'lodash.pick'
  import { FormTypes } from '@/utils/JEditableTableUtil'
  import { JEditableTableMixin } from '@/mixins/JEditableTableMixin'

  export default {
    name: 'OdrmModal',
    mixins: [JEditableTableMixin],
    data() {
      return {
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
          factNo: { rules: [{ required: true, message: '请输入厂区编号!' }] },
          factOdrNo: { rules: [{ required: true, message: '请输入订单批号!' }] },
          styleNo: { rules: [{ required: true, message: '请输入型体编号!' }] },
          brandNo: { rules: [{ required: true, message: '请输入品牌编号!' }] },
          custReqDate: { rules: [{ required: true, message: '请输入客户要求交期!' }] },
          revOdrDate: { rules: [{ required: true, message: '请输入接单日期!' }] },
          sizeKind: { rules: [{ required: true, message: '请输入SIZE种类!' }] },
          odrQty: { rules: [{ required: true, message: '请输入订单双数!' }] },
          ctnPair: { rules: [{ required: true, message: '请输入每箱双数!' }] },
          odrTotCtn: { rules: [{ required: true, message: '请输入总箱数!' }] },
          odrYymm: { rules: [{ required: true, message: '请输入订单年月!' }] },
          odrKind: { rules: [{ required: true, message: '请输入订单类别1.正式 2.销样!' }] },
          odrType: { rules: [{ required: true, message: '请输入订单性质1.正式 2.客户PO 3.预购!' }] },
          mainmatAddRate: { rules: [{ required: true, message: '请输入主料加成比!' }] },
          submatAddRate: { rules: [{ required: true, message: '请输入副料加成比!' }] },
          shipQty: { rules: [{ required: true, message: '请输入出货数量!' }] },
          coinKind: { rules: [{ required: true, message: '请输入币别!' }] },
          unitPrice: { rules: [{ required: true, message: '请输入含税单价!' }] },
          tranRmk: { rules: [{ required: true, message: '请输入用量转档注记!' }] },
          siCheckNum: { rules: [{ required: true, message: '请输入翻箱次数!' }] },
          salRmk: { rules: [{ required: true, message: '请输入销售注记!' }] },
          verifyRmk: { rules: [{ required: true, message: '请输入审核注记!' }] },
        },
        refKeys: ['odrd', ],
        activeKey: 'odrd',
        // 订单子表
        odrdTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '接单双数',
              key: 'cusQty',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '订单主ID',
              key: 'orderId',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '厂区编号',
              key: 'factNo',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '订单批号',
              key: 'factOdrNo',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: 'SIZE 编号',
              key: 'sizeNo',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '生产双数',
              key: 'sizeQty',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '出货数量',
              key: 'shipQty',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
          ]
        },
        url: {
          add: "/order/odrm/add",
          edit: "/order/odrm/edit",
          odrd: {
            list: '/order/odrm/queryOdrdByMainId'
          },
        }
      }
    },
    methods: {
 
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'cusQty', 'taxRate', 'taxPrice', 'chd', 'ahd', 'factNo', 'factOdrNo', 'customNo', 'markNo', 'lineNo', 'styleNo', 'brandNo', 'lastWidth', 'poNo', 'proDept', 'custOdrNo', 'custReqDate', 'revOdrDate', 'purplanNo', 'fCfmDate', 'lCfmDate', 'sizeKind', 'odrQty', 'ctnPair', 'odrTotCtn', 'arrInsDate', 'actInsDate', 'shipDate', 'cancelDate', 'destCountry', 'destPort', 'odrYymm', 'odrKind', 'odrType', 'oriFctryNo', 'outworkFctryNo', 'purplanDate', 'mainmatAddRate', 'submatAddRate', 'factReqDate', 'shipQty', 'coinKind', 'unitPrice', 'userNo', 'modifyDt', 'edition', 'tranRmk', 'sizeNo', 'insTqty', 'skuNo', 'ctnFixRmk', 'siCheckNum', 'productYymm', 'productWeek', 'salRmk', 'proeRmk', 'proeUser', 'proeDt', 'aproDept', 'vaCode', 'lotNo', 'remark', 'salKind', 'payQty', 'verifyRmk', 'verifyUser', 'verifyDt', 'avaRmk', 'matchNo', ))
          // 时间格式化
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.odrd.list, params, this.odrdTable)
        }
      },
 
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        //时间格式化
        return {
          ...main, // 展开
          odrdList: allValues.tablesValue[0].values,
        }
      }
    }
  }
</script>

<style scoped>
</style>