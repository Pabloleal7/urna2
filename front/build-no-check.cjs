const { build } = require('vite');

async function buildNoCheck() {
  try {
    console.log('Construindo projeto sem checagem do TypeScript...');
    await build();
    console.log('Construção concluída com sucesso.');
  } catch (error) {
    console.error('Erro durante a construção:', error);
    process.exit(1);
  }
}

buildNoCheck();
