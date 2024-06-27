<template>
	<div class="card-style">
		<div data-aos="fade-up" data-aos-delay="60">
			<div class="card-inner">
				<router-link :to="`/article/${article.articleId}`">
					<img v-if="article.articleCover" :src="article.articleCover" alt=""/>
					<img v-else src="../../../assets/images/index/card.png" alt="">
					<div class="content">
						<div class="card-info">
							<h2 class="title">{{ article.articleTitle }}</h2>
							<div class="card-description">{{ article.articleSummary }}</div>
						</div>
					</div>
				</router-link>
			</div>
		</div>
	</div>
</template>

<script>
import Icon from "../../icon/Icon";
export default {
	name: 'Card',
	components: {Icon},
	props: {
		article: {}
	},
	computed: {}
}
</script>
