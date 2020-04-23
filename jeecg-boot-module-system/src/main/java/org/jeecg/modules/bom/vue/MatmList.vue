<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="厂区编号">
              <a-input placeholder="请输入厂区编号" v-model="queryParam.factNo"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="材料编号">
              <a-input placeholder="请输入材料编号" v-model="queryParam.matNo"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="材料名称">
              <a-input placeholder="请输入材料名称" v-model="queryParam.matNm"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="材料英文名称">
              <a-input placeholder="请输入材料英文名称" v-model="queryParam.matEngNm"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="材料单位">
              <a-input placeholder="请输入材料单位" v-model="queryParam.matUnit"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('材料表')">导出</a-button>
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
    <matm-modal ref="modalForm" @ok="modalFormOk"></matm-modal>
  </a-card>
</template>

<script>
  import MatmModal from './modules/MatmModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "MatmList",
    mixins:[JeecgListMixin],
    components: {
      MatmModal
    },
    data () {
      return {
        description: '材料表管理页面',
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
            title: '厂区编号',
            align:"center",
            dataIndex: 'factNo'
           },
		   {
            title: '材料编号',
            align:"center",
            dataIndex: 'matNo'
           },
		   {
            title: '材料名称',
            align:"center",
            dataIndex: 'matNm'
           },
		   {
            title: '材料英文名称',
            align:"center",
            dataIndex: 'matEngNm'
           },
		   {
            title: '材料单位',
            align:"center",
            dataIndex: 'matUnit'
           },
		   {
            title: '材料英文单位',
            align:"center",
            dataIndex: 'matEngUnit'
           },
		   {
            title: '材料颜色',
            align:"center",
            dataIndex: 'matColor'
           },
		   {
            title: '英文颜色',
            align:"center",
            dataIndex: 'matEngColor'
           },
		   {
            title: '采购政策',
            align:"center",
            dataIndex: 'purPolicy'
           },
		   {
            title: '平均单价锁定',
            align:"center",
            dataIndex: 'accNo'
           },
		   {
            title: '资材代号',
            align:"center",
            dataIndex: 'accMatno'
           },
		   {
            title: '安全库存',
            align:"center",
            dataIndex: 'safeStkQty'
           },
		   {
            title: '采购提前期',
            align:"center",
            dataIndex: 'leadTime'
           },
		   {
            title: '托外注记',
            align:"center",
            dataIndex: 'preprdRmk'
           },
		   {
            title: '尺寸注记',
            align:"center",
            dataIndex: 'sizeRmk'
           },
		   {
            title: '超交允收',
            align:"center",
            dataIndex: 'overRcvRmk'
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
            title: '单重',
            align:"center",
            dataIndex: 'unitWeight'
           },
		   {
            title: '已订未交量',
            align:"center",
            dataIndex: 'onOrderQty'
           },
		   {
            title: '已收未验量',
            align:"center",
            dataIndex: 'unqcQty'
           },
		   {
            title: '最近进料日',
            align:"center",
            dataIndex: 'lastInDate'
           },
		   {
            title: '最近出库日',
            align:"center",
            dataIndex: 'lastOutDate'
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
            title: '锁定注记',
            align:"center",
            dataIndex: 'lockStatus'
           },
		   {
            title: '锁定日期',
            align:"center",
            dataIndex: 'lockDate'
           },
		   {
            title: '换算单位',
            align:"center",
            dataIndex: 'unitRate'
           },
		   {
            title: '共用注记',
            align:"center",
            dataIndex: 'shareRmk'
           },
		   {
            title: '物性测试注记',
            align:"center",
            dataIndex: 'matphyRmk'
           },
		   {
            title: '检验注记',
            align:"center",
            dataIndex: 'sgsRmk'
           },
		   {
            title: '材料类别编号',
            align:"center",
            dataIndex: 'matkindNo'
           },
		   {
            title: '材料简称',
            align:"center",
            dataIndex: 'uMat'
           },
		   {
            title: '预购注记',
            align:"center",
            dataIndex: 'apRmk'
           },
		   {
            title: '单重审核注记(Y/N)',
            align:"center",
            dataIndex: 'wgtVerifyRmk'
           },
		   {
            title: '单重审核人',
            align:"center",
            dataIndex: 'wgtVerifyUser'
           },
		   {
            title: '单重审核日',
            align:"center",
            dataIndex: 'wgtVerifyDate'
           },
		   {
            title: '关务材料名',
            align:"center",
            dataIndex: 'matNmGw'
           },
		   {
            title: '创建日期',
            align:"center",
            dataIndex: 'creatDate'
           },
		   {
            title: '前次收货日',
            align:"center",
            dataIndex: 'lRecDate'
           },
		   {
            title: '送测进料批次',
            align:"center",
            dataIndex: 'incomQty'
           },
		   {
            title: '盘点单价',
            align:"center",
            dataIndex: 'avgUnitprice'
           },
		   {
            title: '保税注记1.保税 2.非保税',
            align:"center",
            dataIndex: 'taxRmk'
           },
		   {
            title: 'IN_ACC_NO',
            align:"center",
            dataIndex: 'inAccNo'
           },
		   {
            title: '材料材质属性',
            align:"center",
            dataIndex: 'matSpec'
           },
		   {
            title: 'loss比率',
            align:"center",
            dataIndex: 'lossRate'
           },
		   {
            title: '币别',
            align:"center",
            dataIndex: 'coinKind'
           },
		   {
            title: '最小用量',
            align:"center",
            dataIndex: 'minLot'
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
            title: '去掉空格的名称',
            align:"center",
            dataIndex: 'nmTrim'
           },
		   {
            title: '是否测试',
            align:"center",
            dataIndex: 'istest'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/bom/matm/list",
          delete: "/bom/matm/delete",
          deleteBatch: "/bom/matm/deleteBatch",
          exportXlsUrl: "bom/matm/exportXls",
          importExcelUrl: "bom/matm/importExcel",
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