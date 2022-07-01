const { defineConfig } = require("cypress");

module.exports = defineConfig({
  experimentalStudio: true,

  e2e: {
    baseUrl: 'http://localhost:3000',
    env: {
      plinko: "Plinko"
    },
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
  },

  component: {
    devServer: {
      framework: "create-react-app",
      bundler: "webpack",
    },
  },
});
