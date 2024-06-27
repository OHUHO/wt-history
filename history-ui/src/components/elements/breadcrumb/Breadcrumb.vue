<template>
    <div class="breadcarumb-style">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-inner text-center">
                        <h1 class="title theme-gradient h2" v-html="title" data-aos="fade-up"></h1>
                        <ul class="page-list" data-aos="fade-up" data-aos-delay="50">
                            <li class="rn-breadcrumb-item"><router-link :to="`/`">中国历史平台</router-link></li>
                            <li class="rn-breadcrumb-item active">{{currentDynasty}}</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Breadcrumb',
        props: {
            title: {
                type: String,
                default: '设置一个标题'
            },
	        currentDynasty:{
				type: String,
		        default: '朝代'
	        }
        }
    }
</script>
