<template>
	<Layout>
		<div class="rn-section-gap center-wrapper">
			<div class="container">
				<div class="row">
					<Tab/>
				</div>
			</div>
		</div>
	
	</Layout>
</template>

<script>
import Layout from "@/components/common/Layout";
import Tab from "@/components/elements/tab/Tab";
export default {
	name: "CenterPage",
	components: {Layout,Tab},
	data(){
		return{}
	}
}
</script>
