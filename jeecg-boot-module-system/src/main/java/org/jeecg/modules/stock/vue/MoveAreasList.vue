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
            <a-form-item label="库别编号">
              <a-input placeholder="请输入库别编号" v-model="queryParam.stkNo"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="库存年月">
              <a-input placeholder="请输入库存年月" v-model="queryParam.stockYymm"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="订单批号">
              <a-input placeholder="请输入订单批号" v-model="queryParam.factOdrNo"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="存放区编号">
              <a-input placeholder="请输入存放区编号" v-model="queryParam.locNo"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('成品移库')">导出</a-button>
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
    <moveAreas-modal ref="modalForm" @ok="modalFormOk"></moveAreas-modal>
  </a-card>
</template>

<script>
  import MoveAreasModal from './modules/MoveAreasModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "MoveAreasList",
    mixins:[JeecgListMixin],
    components: {
      MoveAreasModal
    },
    data () {
      return {
        description: '成品移库管理页面',
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
            title: '库别编号',
            align:"center",
            dataIndex: 'stkNo'
           },
		   {
            title: '库存年月',
            align:"center",
            dataIndex: 'stockYymm'
           },
		   {
            title: '订单批号',
            align:"center",
            dataIndex: 'factOdrNo'
           },
		   {
            title: '存放区编号',
            align:"center",
            dataIndex: 'locNo'
           },
		   {
            title: '成本部位',
            align:"center",
            dataIndex: 'cpartNo'
           },
		   {
            title: '仓管员编号',
            align:"center",
            dataIndex: 'whmanNo'
           },
		   {
            title: '存放区域流水号',
            align:"center",
            dataIndex: 'locateSeq'
           },
		   {
            title: '现在库存量',
            align:"center",
            dataIndex: 'stockQty'
           },
		   {
            title: '期初库存量',
            align:"center",
            dataIndex: 'startQty'
           },
		   {
            title: '本月入库量',
            align:"center",
            dataIndex: 'inQty'
           },
		   {
            title: '本月出库量',
            align:"center",
            dataIndex: 'outQty'
           },
		   {
            title: '调整入库量',
            align:"center",
            dataIndex: 'fallotInQty'
           },
		   {
            title: '调整出库量',
            align:"center",
            dataIndex: 'fallotOutQty'
           },
		   {
            title: '盘盈亏数量',
            align:"center",
            dataIndex: 'waxQty'
           },
		   {
            title: '存放地点',
            align:"center",
            dataIndex: 'locArea'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/stock/moveAreas/list",
          delete: "/stock/moveAreas/delete",
          deleteBatch: "/stock/moveAreas/deleteBatch",
          exportXlsUrl: "stock/moveAreas/exportXls",
          importExcelUrl: "stock/moveAreas/importExcel",
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