<template>
	<Layout>
		<div class="rn-section-gap maps-wrapper">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<SectionTitle
							title="时空地图"
							description="选择你想查看的朝代对应的地图"
							data-aos="fade-up"
						/>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 mt--40">
						<Map :map-list="mapList"/>
					</div>
				</div>
			</div>
		</div>
	
	</Layout>
</template>

<script>
import Layout from "@/components/common/Layout";
import Map from "@/components/elements/map/Map";
import SectionTitle from "@/components/elements/sectionTitle/SectionTitle";
export default {
	name: "MapsPage",
	components: {Layout, Map, SectionTitle},
	
	data() {
		return {
			mapList:[]
			
		}
	},
	methods:{
		getMaps(){
			this.getRequest("/common/getDynasties",'all').then(resp => {
				if (resp){
					this.mapList = resp.obj
				}
			})
		}
		
	},
	mounted() {
		this.getMaps();
	}
}
</script>

<style scoped>

</style>
