<template>
    <div class="rn-card box-card-style-default">
        <div class="inner">
            <div class="thumbnail">
                <router-link class="image" :to="`/article/${article.articleId}`">
                    <img v-if="article.articleCover" class="w-100" :src="article.articleCover">
	                <img v-else src="../../assets/images/index/slid.jpeg" alt="">
                </router-link>
            </div>
            <div class="content">
                <ul class="rn-meta-list">
                    <li>{{ article.authorName }}</li>
                    <li v-if="article.authorName" class="separator">/</li>
                    <li>{{ article.createTime }}</li>
                </ul>
                <h4 class="title">
                    <router-link :to="`/article/${article.articleId}`">
                        {{ article.articleTitle }}
                    </router-link>
                </h4>
                <!--<ul class="rn-meta-list">
                    <li>
                        <router-link :to="''">
                            {{ blog.author }}
                        </router-link>
                    </li>
                    <li class="separator">/</li>
                    <li>{{ blog.publishedAt }}</li>
                </ul>
                <p class="descriptiion">
                    {{ blog.excerpt }}
                </p>-->
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'BlogPost',
        props: {
            article: {},
        }
    }
</script>
