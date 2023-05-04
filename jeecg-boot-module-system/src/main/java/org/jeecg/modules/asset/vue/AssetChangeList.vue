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
            <a-form-item label="异动单号">
              <a-input placeholder="请输入异动单号" v-model="queryParam.changeNo"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="异动日期">
              <a-input placeholder="请输入异动日期" v-model="queryParam.changeDt"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="原状态">
              <a-input placeholder="请输入原状态" v-model="queryParam.stateOld"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="原厂别">
              <a-input placeholder="请输入原厂别" v-model="queryParam.factOld"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('资产异动表')">导出</a-button>
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
    <assetChange-modal ref="modalForm" @ok="modalFormOk"></assetChange-modal>
  </a-card>
</template>

<script>
  import AssetChangeModal from './modules/AssetChangeModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "AssetChangeList",
    mixins:[JeecgListMixin],
    components: {
      AssetChangeModal
    },
    data () {
      return {
        description: '资产异动表管理页面',
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
            title: '异动单号',
            align:"center",
            dataIndex: 'changeNo'
           },
		   {
            title: '异动日期',
            align:"center",
            dataIndex: 'changeDt'
           },
		   {
            title: '原状态',
            align:"center",
            dataIndex: 'stateOld'
           },
		   {
            title: '原厂别',
            align:"center",
            dataIndex: 'factOld'
           },
		   {
            title: '原绩效中心',
            align:"center",
            dataIndex: 'factCentreOld'
           },
		   {
            title: '原部门',
            align:"center",
            dataIndex: 'deptnoOld'
           },
		   {
            title: '原存放位置',
            align:"center",
            dataIndex: 'locationOld'
           },
		   {
            title: '原保管人',
            align:"center",
            dataIndex: 'custodianOld'
           },
		   {
            title: '原管理经理',
            align:"center",
            dataIndex: 'custodianManagerOld'
           },
		   {
            title: '原使用人',
            align:"center",
            dataIndex: 'custodianUserOld'
           },
		   {
            title: '原存放楼层',
            align:"center",
            dataIndex: 'locationFlOld'
           },
		   {
            title: '原存放区域',
            align:"center",
            dataIndex: 'locationDlOld'
           },
		   {
            title: '原存放厂区',
            align:"center",
            dataIndex: 'locationFactOld'
           },
		   {
            title: '现状态',
            align:"center",
            dataIndex: 'state'
           },
		   {
            title: '现厂别',
            align:"center",
            dataIndex: 'fact'
           },
		   {
            title: '现绩效中心',
            align:"center",
            dataIndex: 'factCentre'
           },
		   {
            title: '现部门',
            align:"center",
            dataIndex: 'deptno'
           },
		   {
            title: '现存放位置',
            align:"center",
            dataIndex: 'location'
           },
		   {
            title: '现保管人',
            align:"center",
            dataIndex: 'custodian'
           },
		   {
            title: '现管理经理',
            align:"center",
            dataIndex: 'custodianManager'
           },
		   {
            title: '现使用人',
            align:"center",
            dataIndex: 'custodianUser'
           },
		   {
            title: '现存放楼层',
            align:"center",
            dataIndex: 'locationFl'
           },
		   {
            title: '现存放区域',
            align:"center",
            dataIndex: 'locationDl'
           },
		   {
            title: '现存放厂区',
            align:"center",
            dataIndex: 'locationFact'
           },
		   {
            title: '备注',
            align:"center",
            dataIndex: 'notes'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/asset/assetChange/list",
          delete: "/asset/assetChange/delete",
          deleteBatch: "/asset/assetChange/deleteBatch",
          exportXlsUrl: "asset/assetChange/exportXls",
          importExcelUrl: "asset/assetChange/importExcel",
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