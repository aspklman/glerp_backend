<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :span="6">
            <a-form-item label="厂区编号">
              <a-input placeholder="请输入厂区编号" v-model="queryParam.factNo"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="6">
            <a-form-item label="型体编号">
              <a-input placeholder="请输入型体编号" v-model="queryParam.styleNo"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('鞋型基本资料表')">导出</a-button>
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
    <stylem-modal ref="modalForm" @ok="modalFormOk"/>

  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import StylemModal from './modules/StylemModal'

  export default {
    name: "StylemList",
    mixins: [JeecgListMixin],
    components: {
      StylemModal
    },
    data () {
      return {
        description: '鞋型基本资料表管理页面',
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
            title: '厂区编号',
            align:"center",
            dataIndex: 'factNo'
          },
          {
            title: '型体编号',
            align:"center",
            dataIndex: 'styleNo'
          },
          {
            title: '客户型体编号',
            align:"center",
            dataIndex: 'articNo'
          },
          {
            title: '型体名称',
            align:"center",
            dataIndex: 'styleNm'
          },
          {
            title: '财务型体简称,财务成本中心维护',
            align:"center",
            dataIndex: 'styleShorten'
          },
          {
            title: '底模编号',
            align:"center",
            dataIndex: 'moldNo'
          },
          {
            title: '超始BOM确认日',
            align:"center",
            dataIndex: 'bomStcfmDate'
          },
          {
            title: '起始用量确认日',
            align:"center",
            dataIndex: 'stdqStcfmDate'
          },
          {
            title: '性别',
            align:"center",
            dataIndex: 'gender'
          },
          {
            title: '楦头编号',
            align:"center",
            dataIndex: 'lastNo'
          },
          {
            title: '英文颜色',
            align:"center",
            dataIndex: 'styleEngColor'
          },
          {
            title: '型体颜色',
            align:"center",
            dataIndex: 'styleColor'
          },
          {
            title: '型体工艺类别',
            align:"center",
            dataIndex: 'solColor'
          },
          {
            title: '商品编号',
            align:"center",
            dataIndex: 'goodsNo'
          },
          {
            title: '序号',
            align:"center",
            dataIndex: 'goodsSeq'
          },
          {
            title: '品牌编号',
            align:"center",
            dataIndex: 'brandNo'
          },
          {
            title: '鞋型类别编号',
            align:"center",
            dataIndex: 'styletypeNo'
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
            title: '型体单价',
            align:"center",
            dataIndex: 'price'
          },
          {
            title: '通用型体名称',
            align:"center",
            dataIndex: 'univModelName'
          },
          {
            title: '报价SIZE编号',
            align:"center",
            dataIndex: 'quotationSizeNo'
          },
          {
            title: '报价楦头肥度',
            align:"center",
            dataIndex: 'quotationLastWidth'
          },
          {
            title: '纸版编号',
            align:"center",
            dataIndex: 'paperNo'
          },
          {
            title: '客户型体代码',
            align:"center",
            dataIndex: 'vampPartNo'
          },
          {
            title: '客户颜色代码',
            align:"center",
            dataIndex: 'cusColorNo'
          },
          {
            title: '来源型体',
            align:"center",
            dataIndex: 'sourNo'
          },
          {
            title: '转印纸注记',
            align:"center",
            dataIndex: 'htpRmk'
          },
          {
            title: '转印纸用量',
            align:"center",
            dataIndex: 'htpQty'
          },
          {
            title: '取消注记',
            align:"center",
            dataIndex: 'cnlRmk'
          },
          {
            title: '取消日期',
            align:"center",
            dataIndex: 'cnlDate'
          },
          {
            title: 'CUSTRAN_RMK',
            align:"center",
            dataIndex: 'custranRmk'
          },
          {
            title: 'CUSTRAN_DT',
            align:"center",
            dataIndex: 'custranDt'
          },
          {
            title: 'ARTIC_NM',
            align:"center",
            dataIndex: 'articNm'
          },
          {
            title: 'BOM审核人',
            align:"center",
            dataIndex: 'bomVerifyUser'
          },
          {
            title: 'BOM审核时间',
            align:"center",
            dataIndex: 'bomVerifyDt'
          },
          {
            title: '型体单位',
            align:"center",
            dataIndex: 'styleUnit'
          },
          {
            title: 'soletypeNo',
            align:"center",
            dataIndex: 'soletypeNo'
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
              list: "/bom/stylem/list",
              delete: "/bom/stylem/delete",
              deleteBatch: "/bom/stylem/deleteBatch",
              exportXlsUrl: "bom/stylem/exportXls",
              importExcelUrl: "bom/stylem/importExcel",
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