-- ============================================================
-- tgc-nvim.lua
-- Project-local neovim config for TerminalGameCollection
-- Loaded via: nvim -u tgc-nvim.lua
-- ============================================================

-- basic editor settings
vim.opt.number = true
vim.opt.expandtab = true
vim.opt.shiftwidth = 4
vim.opt.tabstop = 4

-- rust files conventionally use 4-space indent too, java same
vim.api.nvim_create_autocmd("FileType", {
  pattern = { "rust", "java" },
  callback = function()
    vim.bo.shiftwidth = 4
    vim.bo.tabstop = 4
  end,
})

-- xml uses 2-space indent by convention (pom.xml etc)
vim.api.nvim_create_autocmd("FileType", {
  pattern = { "xml" },
  callback = function()
    vim.bo.shiftwidth = 2
    vim.bo.tabstop = 2
  end,
})

-- ------------------------------------------------------------
-- treesitter: syntax highlighting
-- ------------------------------------------------------------
vim.api.nvim_create_autocmd("FileType", {
  pattern = { "rust", "java", "xml", "toml" },
  callback = function()
    pcall(vim.treesitter.start)
  end,
})

-- ------------------------------------------------------------
-- autocomplete (nvim-cmp)
-- ------------------------------------------------------------
local cmp = require("cmp")
cmp.setup({
  mapping = cmp.mapping.preset.insert({
    ["<Tab>"]   = cmp.mapping.select_next_item(),
    ["<S-Tab>"] = cmp.mapping.select_prev_item(),
    ["<CR>"]    = cmp.mapping.confirm({ select = true }),
  }),
  sources = {
    { name = "nvim_lsp" },
  },
})

local caps = require("cmp_nvim_lsp").default_capabilities()

-- ------------------------------------------------------------
-- LSP: Rust (rust-analyzer)
-- ------------------------------------------------------------
vim.lsp.config("rust_analyzer", {
  cmd          = { "rust-analyzer" },
  filetypes    = { "rust" },
  capabilities = caps,
})
vim.lsp.enable("rust_analyzer")

-- ------------------------------------------------------------
-- LSP: Java (jdtls)
-- ------------------------------------------------------------
vim.lsp.config("jdtls", {
  cmd = { "jdt-language-server",
          "-configuration", vim.fn.expand("~/.cache/jdtls/config"),
          "-data",          vim.fn.expand("~/.cache/jdtls/workspace") },
  filetypes    = { "java" },
  capabilities = caps,
})
vim.lsp.enable("jdtls")

-- ------------------------------------------------------------
-- LSP: XML (lemminx) - for pom.xml
-- ------------------------------------------------------------
vim.lsp.config("lemminx", {
  cmd          = { "lemminx" },
  filetypes    = { "xml" },
  capabilities = caps,
})
vim.lsp.enable("lemminx")

-- ------------------------------------------------------------
-- LSP keymaps (active whenever an LSP attaches to a buffer)
-- ------------------------------------------------------------
vim.api.nvim_create_autocmd("LspAttach", {
  callback = function(ev)
    local opts = { buffer = ev.buf }
    vim.keymap.set("n", "K",          vim.lsp.buf.hover,            opts) -- docs
    vim.keymap.set("n", "gd",         vim.lsp.buf.definition,       opts) -- go to definition
    vim.keymap.set("n", "<leader>rn", vim.lsp.buf.rename,           opts) -- rename
    vim.keymap.set("n", "<leader>ca", vim.lsp.buf.code_action,      opts) -- code actions
    vim.keymap.set("n", "<leader>e",  vim.diagnostic.open_float,    opts) -- expand error/warning
    vim.keymap.set("n", "[d",         vim.diagnostic.goto_prev,     opts) -- prev diagnostic
    vim.keymap.set("n", "]d",         vim.diagnostic.goto_next,     opts) -- next diagnostic
  end,
})
