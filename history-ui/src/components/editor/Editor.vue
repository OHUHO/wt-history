
<!-- 富文本编辑器 mavon-editor
	https://www.npmjs.com/package/mavon-editor
	 -->
<template>
	<mavon-editor
		ref="md"
		placeholder="输入文章内容..."
		:boxShadow="false"
		fontSize="16px"
		style="min-height: 922px;"
		v-model="editor.value"
		toolbarsBackground="#ffffff"
		defaultOpen="edit"
		@imgAdd="imgAdd"
		v-bind="editor">
	</mavon-editor>
	
</template>

<script>

import {mavonEditor} from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'


export default {
	name: 'Editor',
	components: {mavonEditor},
	props: {
		editor: Object
	},
	data() {
		return {}
	},
	mounted() {
		this.$set(this.editor, 'ref', this.$refs.md)
	},
	methods: {
		// 保存图片，返回一个地址
		imgAdd(pos, $file) {
			let that = this
			let userId = JSON.parse(window.sessionStorage.getItem('user')).userId;
			let formdata = new FormData();
			formdata.append('userId',userId);
			formdata.append('image', $file);
			
			this.postRequest("/user/article/articleImg",formdata).then(resp => {
				// 成功后返回图片的url替换到文本原位置
				if (resp) {
					that.$refs.md.$img2Url(pos, resp);
					console.log(resp)
				}
			})
			
		}
	},
	
}
</script>
<style lang="scss" scoped>
/*.write{
	html, body, div, span, applet, object,
	iframe, h1, h2, h3, h4, h5, h6, p, blockquote,
	pre, a, abbr, acronym, address, big, cite, code,
	del, dfn, em, img, ins, kbd, q, s, samp, small,
	strike, strong, sub, sup, tt, var, b, u, i, center,
	dl, dt, dd, ol, ul, li, fieldset,form label, legend,
	table, caption, tbody, tfoot, thead, tr, th, td,article,
	aside, canvas, details, embed, figure,figcaption, footer,
	header, hgroup, menu, nav, output, ruby, section, summary,
	time, mark, audio, video {
		margin: 0;
		padding: 0;
		border: 0;
		font-size: 100%;
		font: inherit;
		vertical-align: baseline;
	}
	
	article, aside, details, figcaption, figure,
	footer, header, hgroup, menu, nav, section {
		display: block;
	}
	body {
		line-height: 1;
	}
	ol, ul {
		list-style: none;
	}
	
	blockquote,q{
		quotes: none;
	}
	blockquote:before,blockquote:after, q:before, q:after {
		content: '';
	}
	table {
		border-collapse: collapse;
		border-spacing: 0;
	}
}*/



</style>
