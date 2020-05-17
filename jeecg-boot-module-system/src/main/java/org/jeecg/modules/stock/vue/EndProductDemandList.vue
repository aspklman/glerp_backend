<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="factNo">
              <a-input placeholder="请输入factNo" v-model="queryParam.factNo"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="proDept">
              <a-input placeholder="请输入proDept" v-model="queryParam.proDept"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="订单编号">
              <a-input placeholder="请输入订单编号" v-model="queryParam.订单编号"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="型体">
              <a-input placeholder="请输入型体" v-model="queryParam.型体"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="颜色">
              <a-input placeholder="请输入颜色" v-model="queryParam.颜色"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('成品需求表')">导出</a-button>
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
    <endProductDemand-modal ref="modalForm" @ok="modalFormOk"></endProductDemand-modal>
  </a-card>
</template>

<script>
  import EndProductDemandModal from './modules/EndProductDemandModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "EndProductDemandList",
    mixins:[JeecgListMixin],
    components: {
      EndProductDemandModal
    },
    data () {
      return {
        description: '成品需求表管理页面',
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
            title: 'factNo',
            align:"center",
            dataIndex: 'factNo'
           },
		   {
            title: 'proDept',
            align:"center",
            dataIndex: 'proDept'
           },
		   {
            title: '订单编号',
            align:"center",
            dataIndex: '订单编号'
           },
		   {
            title: '型体',
            align:"center",
            dataIndex: '型体'
           },
		   {
            title: '颜色',
            align:"center",
            dataIndex: '颜色'
           },
		   {
            title: '接单日期',
            align:"center",
            dataIndex: '接单日期'
           },
		   {
            title: '客户交期',
            align:"center",
            dataIndex: '客户交期'
           },
		   {
            title: 'size编号',
            align:"center",
            dataIndex: 'size编号'
           },
		   {
            title: 'size序号',
            align:"center",
            dataIndex: 'size序号'
           },
		   {
            title: '订单数',
            align:"center",
            dataIndex: '订单数'
           },
		   {
            title: '生产数',
            align:"center",
            dataIndex: '生产数'
           },
		   {
            title: '入库数',
            align:"center",
            dataIndex: '入库数'
           },
		   {
            title: '调整数',
            align:"center",
            dataIndex: '调整数'
           },
		   {
            title: '库存数',
            align:"center",
            dataIndex: '库存数'
           },
		   {
            title: '欠数',
            align:"center",
            dataIndex: '欠数'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/stock/endProductDemand/list",
          delete: "/stock/endProductDemand/delete",
          deleteBatch: "/stock/endProductDemand/deleteBatch",
          exportXlsUrl: "stock/endProductDemand/exportXls",
          importExcelUrl: "stock/endProductDemand/importExcel",
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