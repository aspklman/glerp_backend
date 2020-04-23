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
            <a-form-item label="组别编号">
              <a-input placeholder="请输入组别编号" v-model="queryParam.secNo"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="生效日期">
              <a-input placeholder="请输入生效日期" v-model="queryParam.effectiveDate"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="目标产量">
              <a-input placeholder="请输入目标产量" v-model="queryParam.planQty"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="应到人数">
              <a-input placeholder="请输入应到人数" v-model="queryParam.headcount"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('成型目标产量(大罗沙)表')">导出</a-button>
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
    <plan_prod_dls-modal ref="modalForm" @ok="modalFormOk"></plan_prod_dls-modal>
  </a-card>
</template>

<script>
  import Plan_prod_dlsModal from './modules/Plan_prod_dlsModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "Plan_prod_dlsList",
    mixins:[JeecgListMixin],
    components: {
      Plan_prod_dlsModal
    },
    data () {
      return {
        description: '成型目标产量(大罗沙)表管理页面',
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
            title: '组别编号',
            align:"center",
            dataIndex: 'secNo'
           },
		   {
            title: '生效日期',
            align:"center",
            dataIndex: 'effectiveDate'
           },
		   {
            title: '目标产量',
            align:"center",
            dataIndex: 'planQty'
           },
		   {
            title: '应到人数',
            align:"center",
            dataIndex: 'headcount'
           },
		   {
            title: '目标直通率',
            align:"center",
            dataIndex: 'fpyGoal'
           },
		   {
            title: '产线组长',
            align:"center",
            dataIndex: 'lineLeader'
           },
		   {
            title: '产线厂长',
            align:"center",
            dataIndex: 'productionSupervisor'
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
            title: '型体',
            align:"center",
            dataIndex: 'style'
           },
		   {
            title: '型体描述',
            align:"center",
            dataIndex: 'styleDescription'
           },
		   {
            title: '鞋子类型',
            align:"center",
            dataIndex: 'shoeCategory'
           },
		   {
            title: 'PPH目标',
            align:"center",
            dataIndex: 'pphGoal'
           },
		   {
            title: '实到人数',
            align:"center",
            dataIndex: 'actualWorkers'
           },
		   {
            title: '直接人工',
            align:"center",
            dataIndex: 'directLabor'
           },
		   {
            title: '间接人工',
            align:"center",
            dataIndex: 'directLaborN'
           },
		   {
            title: '多能工人数',
            align:"center",
            dataIndex: 'multiSkillWorkers'
           },
		   {
            title: '非多能工人数',
            align:"center",
            dataIndex: 'multiSkillWorkersN'
           },
		   {
            title: '线长度',
            align:"center",
            dataIndex: 'lineLength'
           },
		   {
            title: '节拍时间',
            align:"center",
            dataIndex: 'tt'
           },
		   {
            title: '总周期时间',
            align:"center",
            dataIndex: 'tct'
           },
		   {
            title: 'MFG_LT',
            align:"center",
            dataIndex: 'mfgLt'
           },
		   {
            title: '生日祝福语',
            align:"center",
            dataIndex: 'blessing'
           },
		   {
            title: '目标产量达成率',
            align:"center",
            dataIndex: 'achieveRate'
           },
		   {
            title: '目标B品数',
            align:"center",
            dataIndex: 'bGrade'
           },
		   {
            title: '目标返修数',
            align:"center",
            dataIndex: 'rework'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/scan/plan_prod_dls/list",
          delete: "/scan/plan_prod_dls/delete",
          deleteBatch: "/scan/plan_prod_dls/deleteBatch",
          exportXlsUrl: "scan/plan_prod_dls/exportXls",
          importExcelUrl: "scan/plan_prod_dls/importExcel",
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