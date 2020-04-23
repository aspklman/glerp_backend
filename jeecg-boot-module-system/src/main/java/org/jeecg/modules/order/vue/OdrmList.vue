<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :span="6">
            <a-form-item label="需出货量">
              <a-input placeholder="请输入需出货量" v-model="queryParam.cusQty"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="6">
            <a-form-item label="税率">
              <a-input placeholder="请输入税率" v-model="queryParam.taxRate"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('订单主表')">导出</a-button>
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
        <i class="anticon anticon-info-circle ant-alert-icon"></i>
        <span>已选择</span>
        <a style="font-weight: 600">
          {{ selectedRowKeys.length }}
        </a>
        <span>项</span>
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
    <odrm-modal ref="modalForm" @ok="modalFormOk"/>

  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import OdrmModal from './modules/OdrmModal'

  export default {
    name: "OdrmList",
    mixins: [JeecgListMixin],
    components: {
      OdrmModal
    },
    data () {
      return {
        description: '订单主表管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key: 'rowIndex',
            width: 60,
            align: "center",
            customRender:function (t, r, index) {
              return parseInt(index)+1;
            }
          },
          {
            title: '需出货量',
            align:"center",
            dataIndex: 'cusQty'
          },
          {
            title: '税率',
            align:"center",
            dataIndex: 'taxRate'
          },
          {
            title: '未税价',
            align:"center",
            dataIndex: 'taxPrice'
          },
          {
            title: '合同交期',
            align:"center",
            dataIndex: 'chd'
          },
          {
            title: '实际交期',
            align:"center",
            dataIndex: 'ahd'
          },
          {
            title: '厂区编号',
            align:"center",
            dataIndex: 'factNo'
          },
          {
            title: '订单批号',
            align:"center",
            dataIndex: 'factOdrNo'
          },
          {
            title: '客户编号',
            align:"center",
            dataIndex: 'customNo'
          },
          {
            title: '唛头编号',
            align:"center",
            dataIndex: 'markNo'
          },
          {
            title: '线别编号',
            align:"center",
            dataIndex: 'lineNo'
          },
          {
            title: '型体编号',
            align:"center",
            dataIndex: 'styleNo'
          },
          {
            title: '品牌编号',
            align:"center",
            dataIndex: 'brandNo'
          },
          {
            title: '楦头肥度',
            align:"center",
            dataIndex: 'lastWidth'
          },
          {
            title: '冲销po信息',
            align:"center",
            dataIndex: 'poNo'
          },
          {
            title: '生产部别',
            align:"center",
            dataIndex: 'proDept'
          },
          {
            title: '客户订单编号',
            align:"center",
            dataIndex: 'custOdrNo'
          },
          {
            title: '客户要求交期',
            align:"center",
            dataIndex: 'custReqDate'
          },
          {
            title: '接单日期',
            align:"center",
            dataIndex: 'revOdrDate'
          },
          {
            title: '预计用量编号',
            align:"center",
            dataIndex: 'purplanNo'
          },
          {
            title: '第一次确认日',
            align:"center",
            dataIndex: 'fCfmDate'
          },
          {
            title: '最后确认日',
            align:"center",
            dataIndex: 'lCfmDate'
          },
          {
            title: 'SIZE种类',
            align:"center",
            dataIndex: 'sizeKind'
          },
          {
            title: '订单双数',
            align:"center",
            dataIndex: 'odrQty'
          },
          {
            title: '每箱双数',
            align:"center",
            dataIndex: 'ctnPair'
          },
          {
            title: '总箱数',
            align:"center",
            dataIndex: 'odrTotCtn'
          },
          {
            title: '安排验货日',
            align:"center",
            dataIndex: 'arrInsDate'
          },
          {
            title: '实际验货日',
            align:"center",
            dataIndex: 'actInsDate'
          },
          {
            title: '实际出货日',
            align:"center",
            dataIndex: 'shipDate'
          },
          {
            title: '取消日期',
            align:"center",
            dataIndex: 'cancelDate'
          },
          {
            title: '目的地',
            align:"center",
            dataIndex: 'destCountry'
          },
          {
            title: '目的港口',
            align:"center",
            dataIndex: 'destPort'
          },
          {
            title: '订单年月',
            align:"center",
            dataIndex: 'odrYymm'
          },
          {
            title: '订单类别1.正式 2.销样',
            align:"center",
            dataIndex: 'odrKind'
          },
          {
            title: '订单性质1.正式 2.客户PO 3.预购',
            align:"center",
            dataIndex: 'odrType'
          },
          {
            title: '原订单厂别',
            align:"center",
            dataIndex: 'oriFctryNo'
          },
          {
            title: '代工厂别',
            align:"center",
            dataIndex: 'outworkFctryNo'
          },
          {
            title: '用量产生时间',
            align:"center",
            dataIndex: 'purplanDate'
          },
          {
            title: '主料加成比',
            align:"center",
            dataIndex: 'mainmatAddRate'
          },
          {
            title: '副料加成比',
            align:"center",
            dataIndex: 'submatAddRate'
          },
          {
            title: '厂内交期',
            align:"center",
            dataIndex: 'factReqDate'
          },
          {
            title: '出货数量',
            align:"center",
            dataIndex: 'shipQty'
          },
          {
            title: '币别',
            align:"center",
            dataIndex: 'coinKind'
          },
          {
            title: '含税单价',
            align:"center",
            dataIndex: 'unitPrice'
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
            title: '预计用量版次',
            align:"center",
            dataIndex: 'edition'
          },
          {
            title: '用量转档注记',
            align:"center",
            dataIndex: 'tranRmk'
          },
          {
            title: '暂定版SIZE编号',
            align:"center",
            dataIndex: 'sizeNo'
          },
          {
            title: '验货数量',
            align:"center",
            dataIndex: 'insTqty'
          },
          {
            title: 'SKU NO 开发版本编号',
            align:"center",
            dataIndex: 'skuNo'
          },
          {
            title: '固定装注记1斩刀用量 2订单主档',
            align:"center",
            dataIndex: 'ctnFixRmk'
          },
          {
            title: '翻箱次数',
            align:"center",
            dataIndex: 'siCheckNum'
          },
          {
            title: '生产年月',
            align:"center",
            dataIndex: 'productYymm'
          },
          {
            title: '生产周次',
            align:"center",
            dataIndex: 'productWeek'
          },
          {
            title: '销售注记',
            align:"center",
            dataIndex: 'salRmk'
          },
          {
            title: '生产指令确认注记',
            align:"center",
            dataIndex: 'proeRmk'
          },
          {
            title: '生产指令确认人',
            align:"center",
            dataIndex: 'proeUser'
          },
          {
            title: '生产指令确认时间',
            align:"center",
            dataIndex: 'proeDt'
          },
          {
            title: '实际生产栋别',
            align:"center",
            dataIndex: 'aproDept'
          },
          {
            title: '增值代码',
            align:"center",
            dataIndex: 'vaCode'
          },
          {
            title: '条码批号',
            align:"center",
            dataIndex: 'lotNo'
          },
          {
            title: '备注',
            align:"center",
            dataIndex: 'remark'
          },
          {
            title: '销售类别(财务)',
            align:"center",
            dataIndex: 'salKind'
          },
          {
            title: '请款数量',
            align:"center",
            dataIndex: 'payQty'
          },
          {
            title: '审核注记',
            align:"center",
            dataIndex: 'verifyRmk'
          },
          {
            title: '审核人',
            align:"center",
            dataIndex: 'verifyUser'
          },
          {
            title: '审核时间',
            align:"center",
            dataIndex: 'verifyDt'
          },
          {
            title: '需成品鞋利库',
            align:"center",
            dataIndex: 'avaRmk'
          },
          {
            title: '外包bom配色',
            align:"center",
            dataIndex: 'matchNo'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
        // 请求参数
    	url: {
              list: "/order/odrm/list",
              delete: "/order/odrm/delete",
              deleteBatch: "/order/odrm/deleteBatch",
              exportXlsUrl: "order/odrm/exportXls",
              importExcelUrl: "order/odrm/importExcel",
           },
        }
      },
      computed: {
        importExcelUrl: function(){
          return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
        }
      },


    methods: {

      initDictConfig() {
      }

    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>