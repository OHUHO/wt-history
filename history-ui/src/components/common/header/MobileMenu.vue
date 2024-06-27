<template>
    <div class="popup-mobile-menu">
        <div class="inner">
            <div class="header-top">
                <div class="logo">
                    <router-link to="/">
                        <img class="logo-light" src="../../../assets/images/logo/logo.png" alt="Corporate Logo">
                        <img class="logo-dark" src="../../../assets/images/logo/logo-dark.png" alt="Corporate Logo">
                    </router-link>
                </div>
                <div class="close-menu">
                    <button class="close-button"
                            @click.prevent="AppFunctions.toggleClass('.popup-mobile-menu', 'active')">
                        <Icon name="x" size="21"/>
                    </button>
                </div>
            </div>
            <Nav/>
        </div>
    </div>
</template>

<script>
    import Nav from './Nav'
    import Icon from '../../icon/Icon';
    import AppFunctions from '../../../helpers/AppFunctions'

    export default {
        name: 'MobileMenu',
        components: {Nav, Icon},
        data() {
            return {
                AppFunctions
            }
        },
        mounted() {
            let elements = document.querySelectorAll('.popup-mobile-menu .has-droupdown > a'),
                elementsTwo = document.querySelectorAll('.popup-mobile-menu .with-megamenu > a');

            for (let i in elements) {
                if (elements.hasOwnProperty(i)) {
                    elements[i].onclick = function () {
                        this.parentElement.querySelector('.submenu').classList.toggle('active');
                        this.classList.toggle('open');
                    }
                }
            }

            for (let i in elementsTwo) {
                if (elementsTwo.hasOwnProperty(i)) {
                    elementsTwo[i].onclick = function () {
                        this.parentElement.querySelector('.rn-megamenu').classList.toggle('active');
                        this.classList.toggle('open');
                    }
                }
            }
        }
    }
</script>