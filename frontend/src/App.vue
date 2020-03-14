<template>
  <div id="app">
    <div class="container body">
      <div class="main_container">
        <div class="x_title">
          <router-link exact :to="{name:'home', props:{id: 1}}"><h1 class="text-center">Jack's Table</h1></router-link>
        </div>

        <div class="x_content">
          <router-view/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
