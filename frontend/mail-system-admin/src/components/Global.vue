<template>

</template>

<script>
    const httpUrl = 'http://localhost:8080/api/v1/'
    function commonFun() {
        console.log("公共方法")
    }
    export default {
        name: "Global",
        httpUrl,
        commonFun
    }

</script>

<style scoped>

</style>
