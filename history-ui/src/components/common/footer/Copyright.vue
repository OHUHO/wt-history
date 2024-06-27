<template>
    <div class="copyright-area copyright-style-one">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-6 col-md-8 col-sm-12 col-12">
                    <div class="copyright-left">
                        <ul class="ft-menu link-hover">
                            <li>
                                <router-link to="#">中国历史平台</router-link>
                            </li>
                            <li>
                                <router-link to="#">作者：Xxx</router-link>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-6 col-md-4 col-sm-12 col-12">
                    <div class="copyright-right text-center text-lg-end">
                        <p class="copyright-text">© 中国历史平台 {{ new Date().getFullYear() }}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Copyright'
    }
</script>
