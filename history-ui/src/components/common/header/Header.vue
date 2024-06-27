<template>
    <div>
	    
        <header class="rn-header header-default header-not-transparent header-sticky ">
            <div class="container position-relative">
                <div class="row align-items-center row--0">
                    <div class="col-lg-9 col-md-6 col-4 position-static">
                        <div class="header-left d-flex">
                            <Logo/>
                            <nav class="mainmenu-nav d-none d-lg-block">
                                <!-- 选项组件 -->
                                <Nav/>
                            </nav>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 col-8">
                        <div class="header-right">
	                        
	                        <!--<div class="write">
		                        <Icon name="edit" size="18"></Icon>
		                        <a href="">去传作</a>
	                        </div>-->
	                        
                            <!-- 菜单折叠按钮 -->
                            <div class="mobile-menu-bar ml--5 d-block d-lg-none">
                                <div class="hamberger">
                                    <button class="hamberger-button"
                                            @click.prevent="AppFunctions.toggleClass('.popup-mobile-menu', 'active')">
                                        <Icon name="menu" size="20"/>
                                    </button>
                                </div>
                            </div>
	
	                        <!-- 写作 -->
	                        <div class="my_switcher">
		                        <a href="/write">
			                        <Icon name="edit" size="18"></Icon>
		                        </a>
	                        </div>
	                        
	                        <div class="header-btn">
		                        <a class="btn-default btn-small round"
		                           v-if="!userInfo"
		                           href="/login">
			                        登录
		                        </a>
		                        <a href="/center" v-else>
			                        <img style="width: 40px;height: 40px;border-radius: 50%"
				                        class="userinfo-avatar" src="../../../assets/images/index/card.png" alt="">
		                        </a>
	                        </div>
	                        
	                        <!-- 主题切换 -->
                            <div id="my_switcher" class="my_switcher">
                                <ul>
                                    <li>
                                        <a href="javascript: void(0);"
                                           @click.prevent="AppFunctions.toggleClass('body', 'active-light-mode')">
                                            <img class="light-icon"
                                                 src="../../../assets/images/icons/sun-01.svg"
                                                 alt="Sun images">
                                            <img class="dark-icon"
                                                 src="../../../assets/images/icons/vector.svg"
                                                 alt="Moon Images">
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            
                            
                        </div>
                    </div>
                </div>
            </div>
        </header>
	    
        <!-- 菜单折叠选项 -->
        <MobileMenu/>
	    
        <!-- 网页背景颜色色 -->
        <div>
            <div class="rn-gradient-circle"/>
            <div class="rn-gradient-circle theme-pink"/>
        </div>
        
    </div>
</template>

<script>
    import Icon from '../../icon/Icon'
    import MobileMenu from './MobileMenu'
    import AppFunctions from '../../../helpers/AppFunctions'
    import Nav from './Nav'
    import Logo from '../../elements/logo/Logo'

    export default {
        name: 'Header',
        components: {Logo, Nav, MobileMenu, Icon},
        data() {
            return {
                AppFunctions,
	            userInfo:JSON.parse(window.sessionStorage.getItem('user'))
            }
        },
        methods: {
            toggleStickyHeader() {
                const scrolled = document.documentElement.scrollTop;
                if (scrolled > 100) {
                    AppFunctions.addClass('.header-default', 'sticky');
                } else if (scrolled <= 100) {
                    AppFunctions.removeClass('.header-default', 'sticky');
                }
            },
	        getUserInfo(){
				/*this.getRequest("/common/getUserInfo").then(resp => {
					if (resp){
						// 存储用户信息 user
						console.log(resp)
						const user = resp.data.obj;
						window.sessionStorage.setItem('user', JSON.stringify(user));
					}
				})*/
	        }
        },
        created() {
            window.addEventListener('scroll', this.toggleStickyHeader);
        },
        mounted() {
            this.toggleStickyHeader();
			
			//this.getUserInfo();
        },
        beforeDestroy() {
            window.removeEventListener('scroll', this.toggleStickyHeader);
        }
    }
</script>

<style>

</style>
