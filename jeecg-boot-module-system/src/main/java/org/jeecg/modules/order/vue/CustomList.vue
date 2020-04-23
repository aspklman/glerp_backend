<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="单箱记录注记(一个箱号一条记录)">
              <a-input placeholder="请输入单箱记录注记(一个箱号一条记录)" v-model="queryParam.perCtnRmk"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="同交期箱号相连注记(Y.相连 N.不相连)">
              <a-input placeholder="请输入同交期箱号相连注记(Y.相连 N.不相连)" v-model="queryParam.ctnContinueRmk"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="纸箱规格抓取方式">
              <a-input placeholder="请输入纸箱规格抓取方式" v-model="queryParam.ctnGetKind"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="材积系数">
              <a-input placeholder="请输入材积系数" v-model="queryParam.seqGetKind"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="odrContinueRmk">
              <a-input placeholder="请输入odrContinueRmk" v-model="queryParam.odrContinueRmk"></a-input>
            </a-form-item>
          </a-col>
           </template>
          <a-col :md="6" :sm="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('客户表')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <custom-modal ref="modalForm" @ok="modalFormOk"></custom-modal>
  </a-card>
</template>

<script>
  import CustomModal from './modules/CustomModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "CustomList",
    mixins:[JeecgListMixin],
    components: {
      CustomModal
    },
    data () {
      return {
        description: '客户表管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
           },
		   {
            title: '单箱记录注记(一个箱号一条记录)',
            align:"center",
            dataIndex: 'perCtnRmk'
           },
		   {
            title: '同交期箱号相连注记(Y.相连 N.不相连)',
            align:"center",
            dataIndex: 'ctnContinueRmk'
           },
		   {
            title: '纸箱规格抓取方式',
            align:"center",
            dataIndex: 'ctnGetKind'
           },
		   {
            title: '材积系数',
            align:"center",
            dataIndex: 'seqGetKind'
           },
		   {
            title: 'odrContinueRmk',
            align:"center",
            dataIndex: 'odrContinueRmk'
           },
		   {
            title: 'cbmRate',
            align:"center",
            dataIndex: 'cbmRate'
           },
		   {
            title: '销货会计科目',
            align:"center",
            dataIndex: 'salAccNo'
           },
		   {
            title: '币别',
            align:"center",
            dataIndex: 'coinKind'
           },
		   {
            title: '联络人电话',
            align:"center",
            dataIndex: 'contactTel'
           },
		   {
            title: '负责人',
            align:"center",
            dataIndex: 'cusBoss'
           },
		   {
            title: '法人代表',
            align:"center",
            dataIndex: 'cusLegalp'
           },
		   {
            title: '品质负责人',
            align:"center",
            dataIndex: 'cusQualityp'
           },
		   {
            title: '财务负责人',
            align:"center",
            dataIndex: 'cusFinancep'
           },
		   {
            title: '采购负责人',
            align:"center",
            dataIndex: 'cusPurchasep'
           },
		   {
            title: '负责人电话',
            align:"center",
            dataIndex: 'cusBossTel'
           },
		   {
            title: '法人代表电话',
            align:"center",
            dataIndex: 'cusLegalpTel'
           },
		   {
            title: '采购负责人电话',
            align:"center",
            dataIndex: 'cusPurchasepTel'
           },
		   {
            title: '品质负责人电话',
            align:"center",
            dataIndex: 'cusQualitypTel'
           },
		   {
            title: '财务负责人电话',
            align:"center",
            dataIndex: 'cusFinancepTel'
           },
		   {
            title: '所属行业',
            align:"center",
            dataIndex: 'cusIndustry'
           },
		   {
            title: '设立登记日期',
            align:"center",
            dataIndex: 'cusFounddate'
           },
		   {
            title: '资本型态',
            align:"center",
            dataIndex: 'cusCapitaltype'
           },
		   {
            title: '年营业额',
            align:"center",
            dataIndex: 'cusAnnualsalesamt'
           },
		   {
            title: '资本额',
            align:"center",
            dataIndex: 'cusCapitalamt'
           },
		   {
            title: '厂房面积',
            align:"center",
            dataIndex: 'cusFactarea'
           },
		   {
            title: '厂房',
            align:"center",
            dataIndex: 'cusFactkind'
           },
		   {
            title: '员工状况',
            align:"center",
            dataIndex: 'cusEmployeeDes'
           },
		   {
            title: '主要设备',
            align:"center",
            dataIndex: 'cusMainequipment'
           },
		   {
            title: '主要产品',
            align:"center",
            dataIndex: 'cusMainproduction'
           },
		   {
            title: '原料需求量',
            align:"center",
            dataIndex: 'cusMaterialrequired'
           },
		   {
            title: '客户性质',
            align:"center",
            dataIndex: 'cusCharacter'
           },
		   {
            title: '付款地点',
            align:"center",
            dataIndex: 'cusPayaddress'
           },
		   {
            title: '收款方式',
            align:"center",
            dataIndex: 'cusReceivedkind'
           },
		   {
            title: '付款方式',
            align:"center",
            dataIndex: 'cusPaykind'
           },
		   {
            title: '请款日',
            align:"center",
            dataIndex: 'cusReceiptday'
           },
		   {
            title: '收款日期 ',
            align:"center",
            dataIndex: 'cusReceiptdate'
           },
		   {
            title: '审核状态',
            align:"center",
            dataIndex: 'cusIfapproved'
           },
		   {
            title: '审核人',
            align:"center",
            dataIndex: 'cusApprovedby'
           },
		   {
            title: '审核日期',
            align:"center",
            dataIndex: 'cusApproveddate'
           },
		   {
            title: '厂区编号',
            align:"center",
            dataIndex: 'factNo'
           },
		   {
            title: '客户编号',
            align:"center",
            dataIndex: 'customNo'
           },
		   {
            title: '客户全称',
            align:"center",
            dataIndex: 'customGnm'
           },
		   {
            title: '客户英文名称',
            align:"center",
            dataIndex: 'customEnm'
           },
		   {
            title: '客户简称',
            align:"center",
            dataIndex: 'customFnm'
           },
		   {
            title: '工商备案号',
            align:"center",
            dataIndex: 'customInvno'
           },
		   {
            title: '折扣比率',
            align:"center",
            dataIndex: 'discountRate'
           },
		   {
            title: '付款方式',
            align:"center",
            dataIndex: 'paymentKind'
           },
		   {
            title: '交易条件',
            align:"center",
            dataIndex: 'tradeCondition'
           },
		   {
            title: '集团注记',
            align:"center",
            dataIndex: 'blocRmk'
           },
		   {
            title: '应收款DR(借)',
            align:"center",
            dataIndex: 'recaccDr'
           },
		   {
            title: '应收款CR(贷)',
            align:"center",
            dataIndex: 'recaccCr'
           },
		   {
            title: '折扣款DR(借)',
            align:"center",
            dataIndex: 'disaccDr'
           },
		   {
            title: '折扣款CR(贷)',
            align:"center",
            dataIndex: 'disaccCr'
           },
		   {
            title: '现付折扣',
            align:"center",
            dataIndex: 'cashRebate'
           },
		   {
            title: '客户地址',
            align:"center",
            dataIndex: 'customAddr'
           },
		   {
            title: '联络人',
            align:"center",
            dataIndex: 'contactEer'
           },
		   {
            title: '联络电话',
            align:"center",
            dataIndex: 'vendTel'
           },
		   {
            title: '传真号码',
            align:"center",
            dataIndex: 'vendFax'
           },
		   {
            title: 'EMAIL',
            align:"center",
            dataIndex: 'email'
           },
		   {
            title: '海关客户编号',
            align:"center",
            dataIndex: 'tarCustom'
           },
		   {
            title: '请款明细格式',
            align:"center",
            dataIndex: 'rptFmt1'
           },
		   {
            title: '记重注记',
            align:"center",
            dataIndex: 'wgtRmk'
           },
		   {
            title: '异动人',
            align:"center",
            dataIndex: 'userNo'
           },
		   {
            title: '异动时间',
            align:"center",
            dataIndex: 'modifyDt'
           },
		   {
            title: '香港客户编号',
            align:"center",
            dataIndex: 'hkcustomNo'
           },
		   {
            title: '收货方',
            align:"center",
            dataIndex: 'recCustFnm'
           },
		   {
            title: '收货厂别',
            align:"center",
            dataIndex: 'recFact'
           },
		   {
            title: 'LOSS请款注记',
            align:"center",
            dataIndex: 'lossPayRmk'
           },
		   {
            title: '海关交易注记(1.直接出口 2.转厂 3.内销)',
            align:"center",
            dataIndex: 'cusTradeRmk'
           },
		   {
            title: 'PACKING产生注记',
            align:"center",
            dataIndex: 'packingRmk'
           },
		   {
            title: '纸箱订购注记',
            align:"center",
            dataIndex: 'ctnPurRmk'
           },
		   {
            title: 'PACKINGLIST格式',
            align:"center",
            dataIndex: 'packlistForm'
           },
		   {
            title: '箱号前缀文字',
            align:"center",
            dataIndex: 'textBeforeCtn'
           },
		   {
            title: 'SIZE混装注记(N.不混装/Y.混装)',
            align:"center",
            dataIndex: 'sizeMixRmk'
           },
		   {
            title: 'LOSS产生PACKING注记(N.不产生 Y.产生)',
            align:"center",
            dataIndex: 'lossPackRmk'
           },
		   {
            title: 'LOSS单独装箱注记(N.不单独装/Y.单独装)',
            align:"center",
            dataIndex: 'lossMixRmk'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/order/custom/list",
          delete: "/order/custom/delete",
          deleteBatch: "/order/custom/deleteBatch",
          exportXlsUrl: "order/custom/exportXls",
          importExcelUrl: "order/custom/importExcel",
       },
    }
  },
  computed: {
    importExcelUrl: function(){
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    }
  },
    methods: {
     
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>