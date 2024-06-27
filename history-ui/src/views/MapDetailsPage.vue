<template>
	<div class="map-details-wrapper">
		<img :src="dynasty.dynastyMap" alt="">
	</div>
</template>

<script>
export default {
	name: "MapDetailsPage",
	data(){
		return{
			dynasty:{},
		}
	},
	mounted() {
		// 获取地图
		this.getDynastyByDynastyId(this.$route.params.dynastyId)
		// 设置
		//this.$route.meta.title = '中国历史平台 || ' + this.dynasty.dynastyName
	},
	methods:{
		getDynastyByDynastyId(dynastyId){
			// 发送请求获取地图信息
			this.getRequest("/common/getDynastyByDynastyId",dynastyId).then(resp => {
				if (resp){
					this.dynasty = resp.obj
					// 设置网页title
					document.title = '中国历史平台 || ' + resp.obj.dynastyName
				}
			})
			
		}
	}
}
</script>

<style scoped>

</style>
