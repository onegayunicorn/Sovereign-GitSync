import sqlite3

DB_PATH = "lineage.db"

def get_db_connection():
    conn = sqlite3.connect(DB_PATH)
    conn.row_factory = sqlite3.Row
    return conn

def initialize_db():
    conn = get_db_connection()
    cursor = conn.cursor()
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS lineage_events (
            event_id TEXT PRIMARY KEY,
            event_type TEXT NOT NULL,
            timestamp TEXT NOT NULL,
            data TEXT NOT NULL,
            coherence_delta REAL,
            severity INTEGER
        )
    ''')
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS conflict_genes (
            gene_id TEXT PRIMARY KEY,
            file_path TEXT NOT NULL,
            risk_score REAL
        )
    ''')
    conn.commit()
    conn.close()

# Initialize on startup
initialize_db()
